// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io;

import java.lang.ref.PhantomReference;
import java.io.File;
import java.util.Vector;
import java.util.Collection;
import java.lang.ref.ReferenceQueue;

public class FileCleaningTracker
{
    ReferenceQueue q;
    final Collection trackers;
    volatile boolean exitWhenFinished;
    Thread reaper;
    
    public FileCleaningTracker() {
        this.q = new ReferenceQueue();
        this.trackers = new Vector();
        this.exitWhenFinished = false;
    }
    
    public void track(final File file, final Object marker) {
        this.track(file, marker, null);
    }
    
    public void track(final File file, final Object marker, final FileDeleteStrategy deleteStrategy) {
        if (file == null) {
            throw new NullPointerException("The file must not be null");
        }
        this.addTracker(file.getPath(), marker, deleteStrategy);
    }
    
    public void track(final String path, final Object marker) {
        this.track(path, marker, null);
    }
    
    public void track(final String path, final Object marker, final FileDeleteStrategy deleteStrategy) {
        if (path == null) {
            throw new NullPointerException("The path must not be null");
        }
        this.addTracker(path, marker, deleteStrategy);
    }
    
    private synchronized void addTracker(final String path, final Object marker, final FileDeleteStrategy deleteStrategy) {
        if (this.exitWhenFinished) {
            throw new IllegalStateException("No new trackers can be added once exitWhenFinished() is called");
        }
        if (this.reaper == null) {
            (this.reaper = new Reaper()).start();
        }
        this.trackers.add(new Tracker(path, deleteStrategy, marker, this.q));
    }
    
    public int getTrackCount() {
        return this.trackers.size();
    }
    
    public synchronized void exitWhenFinished() {
        this.exitWhenFinished = true;
        if (this.reaper != null) {
            synchronized (this.reaper) {
                this.reaper.interrupt();
            }
        }
    }
    
    private final class Reaper extends Thread
    {
        Reaper() {
            super("File Reaper");
            this.setPriority(10);
            this.setDaemon(true);
        }
        
        public void run() {
            while (!FileCleaningTracker.this.exitWhenFinished || FileCleaningTracker.this.trackers.size() > 0) {
                Tracker tracker = null;
                try {
                    tracker = (Tracker)FileCleaningTracker.this.q.remove();
                }
                catch (Exception e) {
                    continue;
                }
                if (tracker != null) {
                    tracker.delete();
                    tracker.clear();
                    FileCleaningTracker.this.trackers.remove(tracker);
                }
            }
        }
    }
    
    private static final class Tracker extends PhantomReference
    {
        private final String path;
        private final FileDeleteStrategy deleteStrategy;
        
        Tracker(final String path, final FileDeleteStrategy deleteStrategy, final Object marker, final ReferenceQueue queue) {
            super(marker, queue);
            this.path = path;
            this.deleteStrategy = ((deleteStrategy == null) ? FileDeleteStrategy.NORMAL : deleteStrategy);
        }
        
        public boolean delete() {
            return this.deleteStrategy.deleteQuietly(new File(this.path));
        }
    }
}
