
## 目标
在IDEA上使用Git+Github完成淘淘商城
## IDEA学习笔记

```
  ctrl + Alt + B            快速进入实现类
  F2                        跳转到下一个高亮错误 或 警告位置
  ctrl + Alt + v            自动填充变量
  ctrl + /                  这个是多行代码分行注释，每行一个注释符号
  ctrl + shift + /          这个是多行代码注释在一个块里，只在开头和结尾有注释符号
  Ctrl + Shift + Z          取消撤销 （必备）
  Ctrl + Shift + N          查找类 
  Ctrl + Alt + L            查找文件
  Ctrl + Alt + Enter        将光标移到当前行的上一行
  Shift + Enter             将光标移到当前行的下一行
  Alt + Shift + Up/Down     上/下移一行
  Alt +	鼠标左键             上下拖动 多行编辑
  Alt + Shift + 鼠标左键     多行选择 自定义多行编辑
```

## Git学习笔记
### Git指令

```
git diff readme.txt         在git add之前使用此命令可以查看文件修改的内容 
git log                     显示从最近到最远的提交日志		 查看提交历史
git reflog                  可以查看所有分支的所有操作记录   查看命令历史
git log --pretty=oneline    在一行之内显示提交日志 格式:版本号 message
git reset --hard HEAD^      回退到上一个版本(HEAD表示当前版本)
git reset --hard commitId   指定回到某个版本
git checkout --fileName     就是让这个文件回到最近一次git commit或git add时的状态。
git reset HEAD fileName     把暂存区的修改撤销掉，重新放回工作区
- 分支操作
git branch                  查看分支

git branch <name>           创建分支

git checkout <name>         切换分支

git checkout -b <name>		  创建+切换分支

git merge <name>            合并某分支到当前分支

git branch -d <name>	    	删除分支
```

### Git名词解释
Git工作区（Working Directory）:就是你在电脑里能看到的目录。

版本库（Repository）:工作区有一个隐藏目录.git，这个不算工作区，而是Git的版本库。
Git的版本库里存了很多东西，其中最重要的就是称为stage（或者叫index）的暂存区，还有Git为我们自动创建的第一个分支master，以及指向master的一个指针叫HEAD。

git删除后：
有两个选择，一是确实要从版本库中删除该文件，那就用命令git rm删掉，并且git commit。
另一种情况是删错了，因为版本库里还有呢，所以可以很轻松地把误删的文件恢复到最新版本：
```
 git checkout -- test.txt
```
git checkout其实是用版本库里的版本替换工作区的版本，无论工作区是修改还是删除，都可以“一键还原”。

## Git远程操作

### Git中从远程的分支获取最新的版本到本地有这样2个命令：
 1. git fetch：相当于是从远程获取最新版本到本地，不会自动merge

```
git fetch origin master
git log -p master..origin/master
git merge origin/master
```

以上命令的含义：
首先从远程的origin的master主分支下载最新的版本到origin/master分支上
然后比较本地的master分支和origin/master分支的差别
最后进行合并
上述过程其实可以用以下更清晰的方式来进行：

```
git fetch origin master:tmp
git diff tmp 
git merge tmp
```
 
从远程获取最新的版本到本地的temp分支上之后再进行比较合并
 2. git pull：相当于是从远程获取最新版本并merge到本地

``` 
git pull origin master
```

上述命令其实相当于`git fetch`和`git merge`
在实际使用中，`git fetch`更安全一些,因为在merge前，我们可以查看更新情况，然后再决定是否合并。

### Git关联远程仓库的操作
关联一个远程仓库

```
git remote add origin git@github.com:YotrolZ/helloTest.git
```

删除关联远程仓库

```
git remote remove origin
```

展示所有关联的远程仓库

```
git remote -v
```

### 将本地内容推送到Github
要关联一个远程库，使用命令git remote add origin git@server-name:path/repo-name.git；

关联后，使用命令`git push -u origin master`第一次推送master分支的所有内容；

此后，每次本地提交后，只要有必要，就可以使用命令`git push origin master`推送最新修改；
