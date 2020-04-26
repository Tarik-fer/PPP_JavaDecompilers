import pygments
from pygments import lexers
from Compare import compare
from Decompile import decompile


if __name__ == '__main__':

	action = input("(1) Dekompiliranje\n(2) Usporedivanje datoteka\nOdaberite 1 ili 2: ")
	while action not in ["1", "2"]:
		# action = input("(1) Dekompiliranje\n(2) Usporedivanje datoteka")
		action = input("(1) Dekompiliranje\n(2) Usporedivanje datoteka\nOdaberite 1 ili 2: ")

	if action == "1":
		decompile()
	elif action == "2":
		compare()


