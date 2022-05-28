import sys
from jiayan import load_lm
from jiayan import CRFSentencizer

lm = load_lm('jiayan.klm')
sentencizer = CRFSentencizer(lm)
sentencizer.load('cut_model')
text = sys.argv[1]
print(sentencizer.sentencize(text))