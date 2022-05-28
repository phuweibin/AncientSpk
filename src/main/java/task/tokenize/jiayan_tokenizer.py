import sys

from jiayan import load_lm
from jiayan import CharHMMTokenizer

text = sys.argv[1]
lm = load_lm('jiayan.klm')
tokenizer = CharHMMTokenizer(lm)
print(list(tokenizer.tokenize(text)))
print("end")