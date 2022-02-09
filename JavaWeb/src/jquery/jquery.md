# 1. jQuery基础

&emsp;jQuery是一个快速、小巧、功能丰富的js库，可以简化js的开发。它封装了js相关方法的调用·，简化了js对html DOM的操作。[jQuery官网](https://jquery.com/)

&emsp;**使用jQuery的原因：**

1. 能兼容市面上主流的浏览器。不同浏览器创建异步对象是不同的，但jquery能使用一种方式在不同浏览器上创建不同对象。
2. 免费、开源的轻量级js库，简化js开发。
3. 能处理html/jsp/xml、css、dom、时间、实现动画效果，也能提供异步的Ajax功能。
4. 有成熟的插件可供选择，多种js组件。比如日历组件。
5. 文档手册很全很详细。

使用JS定位DOM对象的三种常用方式：

* 通过id属性：getElementById()
* 通过class属性：getElementsByClassName()
* 通过标签名：getElementsTagName()

&emsp;js的方法名长，大小写组合多，导致编写代码的效率低且容易出错。jquery分别使用```${"#id"}```，```${".className"}```，```${"tagName"}```封装了上面的三个方法。



## 1.1 使用jquery

**使用jquery：**在[官网](https://jquery.com/)下载后，在项目中引入，就可以使用了。

**示例：**

1. 创建html页面
2. 在html页面中引入jquery.js
3. 使用jquery封装的方法

```html
<html lang="zn">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script src="../js/jquery-3.6.0.js"></script>
        <script>
            $(document).ready(function(){
                alert("Hello jquery!")
            })

            /**
             * 1. $：jquery中的函数名称，document是函数的参数，作用是将document对象变成jquery可以使用的对象。
             * 2. ready：jquery中的函数。是准备的意思。当页面的dom对象加载成功后，会执行ready函数的内容，相当于js中的onload事件。
             * 3. function：自定义函数，表示ready后要执行的功能
             */

            // 上述方法的简写，同时这也是jquery的入口函数
            $(function(){
                alert("jquery 方法的入口")
            })
        </script>
    </head>
    <body>

    </body>
</html>
```

&emsp;```$(document).ready()```、```$()```、```jQuery()```、```window.jQuery```四者等价的。最常见的就是第二种```$()```。



## 1.2 DOM对象和jQuery对象

&emsp;DOM对象是js创建的对象。jQuery对象也是jQuery创建的对象，jquery表示的对象都是数组。两类对象是可以相互转化的。语法如下：

* DOM对象--->jQuery对象：$(DOM对象)
* jquery对象--->DOM对象：从数组只能获取第一个对象，使用[0]或者get(0)

&emsp;之所以两者可以相互转换，想使用DOM对象的属性和方法，就必须是DOM对象，想使用jquery提供的函数，就必须是jquery对象。提供两者之间的相互转换，可以更加灵活的编写代码。

**DOM对象转jquery对象：**

```html
<html lang="zn">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script src="../js/jquery-3.6.0.js"></script>
        <script>
            function btnClick(){
                // 1. 使用js的方法获取到DOM对象
                var domBtn = document.getElementById("btn");
                // 2. 使用jquery函数$()将DOM对象转为jquery对象
                var $btn=$(domBtn);
                // 3. 调用jquery方法val()，获取jquery对象内容(value)
                alert("2="+$btn.val())
            }
        </script>
    </head>
    <body>
        <div>
            <input type="button" id="btn" value="我要成为jquery对象" onclick="btnClick()"/>
        </div>

    </body>
</html>
```



**jquery对象转DOM对象：**

```html
<html lang="zn">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script src="../js/jquery-3.6.0.js"></script>
        <script>
            function btnClick(){
                // 1. 使用jquery语法获取页面中的dom对象，并将其转为jquery对象
                var obj = $("#txt");

                // 2. jquery对象是在数组，可以使用[0]或get[0]的方法将jquery对象转为DOM对象
                var domObj = obj[0];

                // 3. 实现平方，因为需要使用value属性，所以需要转为DOM对象
                var i = domObj.value;
                objObj.value=i*i;
            }

        </script>
    </head>
    <body>
        <div>
            <input type="button" value="计算平方" onclick="btnClick()">
            <input type="text" id="txt" placeholder="整数">
        </div>

    </body>
</html>
```





# 2. 选择器

&emsp;选择器就是定位条件，使用jquery函数定位满足条件的DOM对象。定位DOM对象后就可以使用jquery函数操作DOM对象。

## 2.1 基本选择器

1. id选择器：$("#DOM对象的id值")
2. class选择器：$(".class名")
3. 标签选择器：$("标签名)
4. 所有选择器：$("*")，选择页面中所有的DOM对象
5. 组合选择器：$("#id,.class,标签名")

**示例：**

```html
<html lang="zn">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <style>
            div{
                background-color: #FF7700;
                width: 200px;
                height: 100px;
            }
        </style>

        <script src="../js/jquery-3.6.0.js"></script>
        <script>
            function fun1(){
                let $div = $("#oneDiv");
                $div.css("background","#43CD80")

            }

            function fun2(){
                let $div = $(".twoDiv");
                $div.css("background","#6A5ACD")

            }

            function fun3(){
                let $div = $("div");
                // jquery的对象是一个数字，jquery操作会操作数组中的所有成员
                $div.css("background","#FF6A6A")
            }

            function fun4(){
                let $div = $("*");
                $div.css("background","#FF3030")
            }

            function fun5(){
                let $div = $("#oneDiv,.twoDiv");
                $div.css("background","#9A32CD")
            }
        </script>
    </head>
    <body>
        <div id="oneDiv">id的div</div>
        <br>
        <div class="twoDiv">class的div</div>
        <br>
        <div id="tagNameDiv">标签的div</div>

        <input type="button" value="通过id选择器获取dom对象" onclick="fun1()">
        <input type="button" value="通过class选择器获取dom对象" onclick="fun2()">
        <input type="button" value="通过标签选择器获取dom对象" onclick="fun3()">
        <input type="button" value="选取当前页面中所有DOM对象" onclick="fun4()">
        <input type="button" value="组合选择，通过id和class选择DOM对象" onclick="fun5()">
    </body>
</html>
```



## 2.2 表单选择器

&emsp;表单选择器是指对文本框、密码框、复选框、下拉列表等元素进行定位。表单选择器是为了更加方便的操作表单，它通过元素的类型来进行定位。语法规则为：```$(":type 属性值")```，例如：```$(":text")```选取所有的单行文本框、```$(":password")```选取所有的密码框。无论有无form标签，表单选择器都可以使用。

*注意```$(":tr")```不能使用，因为tr不是input标签。*

**示例：**

```html
<!DOCTYPE html>
<html lang="zn">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script src="../js/jquery-3.6.0.js"></script>
        <script>
            function btnFun1(){
                var $text = $(":text");
                alert($text.val())
            }

            function btnFun2(){
                var $radio = $(":radio");
                for (let i = 0; i <$radio.length ; i++) {
                    alert($radio[i].value)
                }
            }

            function btnFun3(){
                var checkbox = $(":checkbox");
                for (let i = 0; i <checkbox.length ; i++) {
                    alert(checkbox[i].value)
                }

                alert("直接使用jQuery对象的val()方法")
                for (let i = 0; i <checkbox.length ; i++) {
                    var $o = $(checkbox[i]);
                    var hobby = $o.val();
                    alert(hobby)
                }
            }
        </script>
    </head>
    <body>
        <input type="text" value="表单选择器     type:text">
        <br>
        性别：<br>
        <input type="radio" value="man" />男<br>
        <input type="radio" value="woman" />女
        <br>
        爱好：
        <br>
        <input type="checkbox" value="movie">看电影
        <input type="checkbox" value="play game">玩游戏
        <input type="checkbox" value="music">听音乐
        <br>

        <input type="button" value="读取text的值" onclick="btnFun1()">
        <input type="button" value="读取audio的值" onclick="btnFun2()">
        <input type="button" value="读取checkbox的值" onclick="btnFun3()">
    </body>
</html>
```





# 3. 过滤器

&emsp;过滤器就是过滤条件，对已经定位到数组中的DOM对象进行过滤筛选，过滤器也是一个字符串。过滤条件不能独立出现在jQuery函数，只能出现在选择器后方。主要由有两种过滤器：

1. 基本过滤器
2. 表单属性过滤器

## 3.1 基本过滤器

**语法：**

```js
$("选择器:first")	// first表示取出数组中第一个成员

$("选择器:last")	// last表示取出数组中最后一个成员

$("选择器:eq(数组索引)")	// 根据索引取出数组中对应索引的成员

$("选择器:lt(数组索引)")	// 取出数组中小于给定索引的全部成员

$("选择器:gt(数组索引)")	// 取出数组中大于给定索引的全部成员
```



**示例：**

```html
<html lang="zn">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script src="../js/jquery-3.6.0.js"></script>
        <script>
            /**
             * $("选择器:first")    // first表示取出数组中第一个成员
             * $("选择器:last")    // last表示取出数组中最后一个成员
             * $("选择器:eq(数组索引)")    // 根据索引取出数组中对应索引的成员
             * $("选择器:lt(数组索引)")    // 取出数组中小于给定索引的全部成员
             * $("选择器:gt(数组索引)")    // 取出数组中大于给定索引的全部成员
             */

            // 1. jQuery入口函数
            $(function () {
                // 2. 为按钮绑定事件
                $("#btn1").click(fun1)
                $("#btn2").click(fun2)
                $("#btn3").click(fun3)
                $("#btn4").click(fun4)
                $("#btn5").click(fun5)
                $("#btn6").click(fun6)

                // 3.定义方法
                function fun1() {
                    // 4. 定义过滤器
                    $("div:first").css("background-color", "#6495ED")
                }

                function fun2() {
                    $("div:last").css("background-color", "#9370DB")
                }

                function fun3() {
                    $("div:eq(3)").css("background-color", "#90EE90")
                }

                function fun4() {
                    $("div:lt(3)").css("background-color", "#FFD700")
                }

                function fun5() {
                    $("div:gt(3)").css("background-color", "#FF4500")
                }

                function fun6() {
                    $("div").css("background-color", "#FF7700")
                }
            })

        </script>
        <style>
            div {
                background-color: #FF7700;
            }
        </style>
    </head>
    <body>
        <P>以下均已索引表示</P>
        <div id="zero">第0个div</div>
        <div id="one">第1个div</div>

        <div>
            第2个div
            <div class="son">
                第3个div
            </div>
            <div class="son">
                第4个div
            </div>
        </div>

        <div>
            第5个div
        </div>

        <span>
            我是span
        </span>
        <hr>

        <p>功能按钮</p>
        <input type="button" id="btn1" value="选择第一个div"><br>
        <input type="button" id="btn2" value="选择最后一个div"><br>
        <input type="button" id="btn3" value="选择索引为3的div"><br>
        <input type="button" id="btn4" value="选择索引大于3的div"><br>
        <input type="button" id="btn5" value="选择索引小于3的div"><br>
        <input type="button" id="btn6" value="还原最开始的样子"><br>
    </body>
</html>
```





## 3.2 表单对象属性过滤器

&emsp;表单属性过滤器：根据表单中dom对象的**状态情况**，定位dom对象。dom对象的状态情况有以下几种：

1. 启用状态：enabled
2. 不可用状态：disabled
3. 选中状态：checked，例如radio、checkbox

**语法：**

* 选择可用文本框：```$(":text:enabled")```
* 选择不可用的文本框：```$(":text:disabled")```
* 选择复选框选中的元素：```$(":checkbox:checked")```
* 选择指定下拉列表的被选中元素：```选择器>option:selected```



**示例：**

html页面

```html
<!DOCTYPE html>
<html lang="zn">
    <head>
        <meta charset="UTF-8">
        <title>表单属性过滤器</title>
        <script src="../js/jquery-3.6.0.js"></script>
        <script src="../js/08表单属性过滤器.js"></script>
    </head>
    <body>
        <p>文本框</p>
        <input type="text" id="text1" value="text1"><br>
        <input type="text" id="text2" value="text2" disabled><br>
        <input type="text" id="text3" value="text3"><br>
        <input type="text" id="text4" value="text4" disabled><br>

        <p>复选框</p>
        <input type="checkbox" value="游泳">游泳<br>
        <input type="checkbox" value="健身" checked>健身<br>
        <input type="checkbox" value="电子游戏">电子游戏<br>

        <p>下拉列表</p>
        <select id="lang">
            <option value="java" selected>java语言</option>
            <option value="go">go语言</option>
            <option value="php">php语言</option>
        </select>

        <p>功能按钮</p>
        <input type="button" id="btn1" value="所有可用的text设值为：hello">
        <input type="button" id="btn2" value="显示所有被选中的复选框的值">
        <input type="button" id="btn3" value="显示下拉列表选中的值">
    </body>
</html>
```



外部js代码

```js
// jquery入口函数
$(function (){
    // 1. 获取按钮DOM对象
    var btn1 = $("#btn1")
    var btn2 = $("#btn2")
    var btn3 = $("#btn3")

    // 2. 使用过滤器筛选
    btn1.click(function (){
        // 选中所有可用的文本框
        var $text = $(":text:enabled");
        $text.val("hello");
    })

    btn2.click(function (){
        // 选中复选框被选中的值
        var $checkbox = $(":checkbox:checked");
        var str="";
        for (let i = 0; i <$checkbox.length; i++) {
            str = str+$checkbox[i].value+"   ";
        }
        alert(str)
    })

    btn3.click(function (){
        // 选择下拉列表选中的值
        var $select = $("select>option:selected");
        alert($select.val())
    })
})
```





# 4. 函数

&emsp;函数是jquery提供的功能，在jquery中有成百上千个函数，在此介绍一些常用的函数。

## 4.1 val函数

&emsp;操作数组中DOM对象的value属性。语法如下：

1. ```$("选择器").val()```：无参调用，读取数组中第一个DOM对象的value属性值
2. ```$("选择器").val(值)```：有参调用，对数组中所有DOM对象的value属性进行赋值



## 4.2 text函数

&emsp;操作数组中所有DOM对象的【文字显示内容属性】



### 4.1.3 attr函数



1. 第一组
   * val
   * text
   * attr
2. 第二组

# 5. 事件

&emsp;jQuery中给dom对象绑定事件的方式由两种：

1. ```$(选择器).事件名称(事件处理函数)```
2. 

## 5.1 定义元素监听事件

**语法：** 

```js
// 处理函数可以在小括号中编写，也可以是一个函数名，在别处定义函数
$(选择器).监听事件名称(处理函数)	// 选择器选择多个DOM对象并绑定事件时，就为多个DOM对象绑定了事件
```

&emsp;在jquery中监听事件名称是js事件去掉```on```后的内容，比如```onclick```就是```click```。比如要为页面中所有的```button```绑定一个```onclick```，处理函数为```fun1```:

```js
$("button").click(fun1)
```

**示例：** 

```html
<html lang="zn">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script src="../js/jquery-3.6.0.js"></script>
        <script>
            // jQuery入口函数
            $(function (){
                $(":button").click(fun1)
                function fun1(){
                    alert("jQuery事件绑定")
                }
            })
        </script>
    </head>
    <body>
        <input type="button" value="点击">
    </body>
</html>
```





# 6. Ajax

