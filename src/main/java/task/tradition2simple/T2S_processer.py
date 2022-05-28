import sys
from opencc import OpenCC


text = sys.argv[1]
cc = OpenCC('t2s')
print(cc.convert(text))