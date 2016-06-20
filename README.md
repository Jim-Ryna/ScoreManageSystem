# ScoreManageSystem

## 基本介绍
####一个简陋的成绩管理系统，可以实现基本的增删改查，用到了`mysql6.3`和`intellij idea2016.1.3`进行开发</br></br></br></br>

###界面（1） 输入界面
####实现对成绩的录入
![](https://github.com/Jim-Ryna/ScoreManageSystem/blob/master/input.gif)</br></br>

###界面（2） 查询界面
####实现对成绩的匹配查询，允许对数据进行删除修改
![](https://github.com/Jim-Ryna/ScoreManageSystem/blob/master/query.gif)</br></br>

###界面（3） 排序界面
####实现对成绩的各种排序
![](https://github.com/Jim-Ryna/ScoreManageSystem/blob/master/sort.gif)</br></br></br></br>

##MySQL表总揽
###class
<table>
<tbody>
<tr><td><em>列名</em></td><td><em>数据类型（精度范围）</em></td><td><em>空/非空</em></td><td><em>约束条件</em></td></tr>
<tr><td>classno</td><td>varchar(10)</td><td>非空</td><td>唯一、主键</td></tr>
</tbody>
</table>
</br>

###student
<table>
<tbody>
<tr><td><em>列名</em></td><td><em>数据类型（精度范围）</em></td><td><em>空/非空</em></td><td><em>约束条件</em></td></tr>
<tr><td>nubmer</td><td>varchar(30)</td><td>非空</td><td>唯一、主键</td></tr>
<tr><td>name</td><td>varchar(30)</td><td>非空</td><td>唯一、主键</td></tr>
<tr><td>class</td><td>varchar(10)</td><td>非空</td><td>班级（外键）</td></tr>
</tbody>
</table>
</br>

###objects
<table>
<tbody>
<tr><td><em>列名</em></td><td><em>数据类型（精度范围）</em></td><td><em>空/非空</em></td><td><em>约束条件</em></td></tr>
<tr><td>objname</td><td>varchar(30)</td><td>非空</td><td>唯一、主键</td></tr>
</tbody>
</table>
</br>

###scores
<table>
<tbody>
<tr><td><em>列名</em></td><td><em>数据类型（精度范围）</em></td><td><em>空/非空</em></td><td><em>约束条件</em></td></tr>
<tr><td>number</td><td>varchar(30)</td><td>非空</td><td>唯一、主键</td></tr>
<tr><td>objkey</td><td>varchar(30)</td><td>无限制</td><td>科目名称（外键）</td></tr>
<tr><td>number</td><td>varchar(30)</td><td>无限制</td><td>科目成绩（外键）</td></tr>
<tr><td>number</td><td>varchar(30)</td><td>无限制</td><td>总分</td></tr>
</tbody>
</table>
