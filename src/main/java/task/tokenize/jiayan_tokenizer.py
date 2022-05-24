from jiayan import load_lm
from jiayan import CharHMMTokenizer

text = input()
lm = load_lm('jiayan.klm')
tokenizer = CharHMMTokenizer(lm)
print(list(tokenizer.tokenize(text)))
print("end")