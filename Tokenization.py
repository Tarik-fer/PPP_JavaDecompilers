import re
import pygments
from pygments import lexers


def tokenize(source_text):
	java_lexer = lexers.get_lexer_by_name("java")
	file_tokens = pygments.lex(source_text, java_lexer) # vraca tokene u obliku tuple-a, (ime tokena, string tokena u kodu)
	token_string = ""

	for token, literal in file_tokens:      # izbacivanje package-a, import-ova i komentara
		# print("Token : " + str(token) + " -> " + str(literal))
		if str(token) in ["Token.Keyword.Namespace", "Token.Comment.Multiline", "Token.Comment.Single", "Token.Name.Namespace"]:
			continue
		if re.match(r"^[\s]+$", literal):       # ako je token samo indent-ovi, to ce stvoriti token "Text" te krivo povecava slicnost
			continue
		# za halestead, operands - name, literal.string, konstante
		# operators - name.function, punctuation, keyword.type, operator, name.class
		token = str(token).replace("Token.", "")
		token = str(token).replace(".", "")
		token_string += str(token)
		# print(str(token) + ", " + literal)

	print(token_string)
	return token_string
