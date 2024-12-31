# an introduce to RISCM
riscm is part of project Mindustack.riscm is compiled from llvmir and then compiled to binary  that loaded in mindustry.memoryBank and interpreted by mindustack virtual machine.
## binary layout
#### inst id
51 50 49 48 47 46 45 44 43 42
#### r0
41 40 39 38 37 36 35 34 33 
#### r1
32 31 30 29 28 27 26 25 24
#### r2
23 22 21 20 19 18 17 16 15
#### immediate 
31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0
#### unsigned imm &op
13 12 11 10 9 8 7 6 5 4 3 2 1 0
## register layout
##### runtime
0: zero 1: ra 2: sp 3: gp1 4: gp2 5: gp3 6: tp 7: pc
##### temp

[//]: # (32-63)
##### args

[//]: # (64-95)
##### saved

[//]: # (96-127)
## shared parser
```asm
read pc bank1 7
op shr memId pc 9
getlink mem memId
op mod localInstId pc 512
read inst mem localInstId

op shr instId inst 42
op shr Reg0id inst 33
op mod Reg0id Reg0id 512
op shr Reg1id inst 24
op mod Reg1id Reg1id 512

op shr Reg2id inst 15
op mod Reg2id Reg2id 512
op mod imm inst 0xffffffff
op mod uimm inst 0x4fff
set @counter instid

wait:
set pTick @tick
check:
jump check  equal pTick @tick
```
## instruction

### set Reg0 Reg1
mov reg1 to reg0
```asm
read v0 bank1 Reg1id
write v0 bank1 Reg0id
jump wait always
```
### set Reg0 immInt
load imm to Reg0
```asm
jump set-immInt lessThanEq imm 0x8fffffff
op sub imm imm 0x100000000
set-immInt:
write imm bank1 Reg0id
```
### set Reg0 immFloat
load imm to reg0
float:
31 | 30 29 28 27 26 25 24 23 | 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0
```asm
op shr v1 imm 23
op mod v1 v1 0xff
op sub v1 v1 0x7f
op mod v2 imm 0x4fffff 
op add v2 v2 0x800000

op shl v0  v2 v1
jump set-immFloat lessThanEq imm 0x8fffffff
op mul v0 v0 -1
set-immFloat:
write v0 bank1 Reg0id
jump wait always
```  
### load Reg0 Reg1 uimm
load memory[reg1+uimm] to reg0
```asm
read addr bank1 Reg1id
op add addr addr uimm
op shr memId addr 9
getlink mem memId
op mod localInstId addr 512

read v0 mem localInstId
store v0 bank1 Reg0id
jump wait always
```
### store Reg0 Reg1 uimm
store reg0 to memory[reg1+uimm]
```asm
read addr bank1 Reg1id
op add addr addr uimm
op shr memId addr 9
getlink mem memId
op mod localInstId addr 512

read v0 bank1 Reg0id
store v0 mem localInstId
jump wait always
```
### op {operation} Reg0 Reg1 Reg2 
```asm
read v1 bank1 Reg1id
read v2 bank1 Reg2id
set @counter uimm
op-return:
write v0 bank1 Reg0id
jump wait always
# add
op add v0 v1 v2
jump op-return always
# sub
op sub v0 v1 v2
jump op-return always
# mul
op mul v0 v1 v2
jump op-return always
# div
op div v0 v1 v2
jump op-return always
# mod
op mod v0 v1 v2
jump op-return always
# eq
op equal v0 v1 v2
jump op-return always
# ne
op notEqual v0 v1 v2
jump op-return always
# lt
op lessThan v0 v1 v2
jump op-return always
# le
op lessThanEq v0 v1 v2
jump op-return always
# gt
op greaterThan v0 v1 v2
jump op-return always
# ge
op greaterThanEq v0 v1 v2
jump op-return always
# and
op and v0 v1 v2
jump op-return always
# or
op or v0 v1 v2
jump op-return always
# xor
op xor v0 v1 v2
jump op-return always
# shl
op shl v0 v1 v2
jump op-return always
# shr
op shr v0 v1 v2
jump op-return always
```
### jump addr {condition}
```asm
read v0 bank1 Reg0id
read v1 bank1 Reg1id
read v2 bank1 Reg2id
set pc v0
set @counter uimm
juno-false-return:
op add pc pc 1
jump-true-return:
store pc bank1 7
jump wait always
# always
jump jump-true-return always
# equal
jump jump-true-return equal v1 v2
jump jump-false-return always
# notEqual
jump jump-true-return notEqual v1 v2
jump jump-false-return always
# lessThan
jump jump-true-return lessThan v1 v2
jump jump-false-return always
# lessThanEq
jump jump-true-return lessThanEq v1 v2
jump jump-false-return always
# greaterThan
jump jump-true-return greaterThan v1 v2
jump jump-false-return always
# greaterThanEq
jump jump-true-return greaterThanEq v1 v2
jump jump-false-return always
# and
jump jump-true-return and v1 v2
jump jump-false-return always
# or
jump jump-true-return or v1 v2
jump jump-false-return always
# xor
jump jump-true-return xor v1 v2
jump jump-false-return always
```