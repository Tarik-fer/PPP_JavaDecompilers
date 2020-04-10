import hashlib


def h11(word):
	return int(hashlib.md5(word.encode('utf8')).hexdigest()[:9], 16)


# word.encode('utf-8')

def createFingerprints(text, k):
	fingerprintArr = []
	for i in range(0, len(text) - k):
		fingerprintArr.append(h11(text[i: i + k]))
	return fingerprintArr


def findMinIndex(fingerprints):
	minimum = fingerprints[0]
	minIndex = 0
	for i in range(0, len(fingerprints)):
		if int(fingerprints[i]) < int(minimum):
			minimum = fingerprints[i]
			minIndex = i

	return minIndex


def winnowingAlgorithm(fingerprints, w):        # main function of winnowing algorithm

	winnowingArr = []
	minRelativeIndex = -1
	minRelativeValue = 0
	for i in range(0, len(fingerprints) - w):
		if minRelativeIndex == -1:
			minRelativeIndex = findMinIndex(fingerprints[i:i + w])
			minRelativeValue = fingerprints[i + minRelativeIndex]
			winnowingArr.append(minRelativeValue)
			minRelativeIndex -= 1
		else:
			minRelativeIndex -= 1
			if int(minRelativeValue) > int(fingerprints[i + w - 1]):
				minRelativeIndex = w - 1
				minRelativeValue = fingerprints[i + w - 1]
				winnowingArr.append(minRelativeValue)
	return winnowingArr

