# CashRegisterInterpreter
For practicing design patterns

『Java言語で学ぶデザインパターン入門』のInterpreterパターンを練習としてまねています。
レジをイメージした簡単なプログラムを作成しました。
Javaの実行環境があれば動かせます。

## usage
program.txtを編集し実行する。(そのままでも実行可能)  
ex: repeat 2 pay 100 end  

### comand
reg : 取込み  (ex: reg 200)  
reg : 取込み  (ex: reg 200)  
del : 取消し  (ex: del 300)  
pay : 支払い  (ex: pay 500)  
repeat : 繰返し (ex: repeat 2 pay 100 end)

