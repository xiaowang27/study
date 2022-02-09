# DOM模型

&emsp;DOM全称是Document Object Model，为文档对象模型。就是将文档中的标签、属性、文本当作对象来管理。

* Document文档树管理了所有的HTML文档内容
* Document是一种树结构的文档，有层级关系
* DOM可以把所有的标签都对象化
* 可以通过DOM访问所有的标签对象



## 1. DOM对象中的常用方法

&emsp;实际中常用的DOM对象方法：

| 方法名                                  | 作用                           | 注意事项 |
| --------------------------------------- | ------------------------------ | -------- |
| document.getElementById(elementId)      | 通过标签的id属性来查找标签     |          |
| document.getElementsByName(elementName) | 通过标签的name属性来查找标签   |          |
| document.getElementsByTagName(tagName)  | 通过标签名来查找标签           |          |
| document.createElement(tagName)         | 通过给定的标签名，创建标签对象 |          |

