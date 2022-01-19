# Ajax

## 1.1 全局刷新和局部刷新

**全局刷新：** 整个浏览器页面被新的数据覆盖。在网络中传输大量数据，浏览器需呀重新加载，渲染页面。

**局部刷新：** 在页面内部发起请求获取数据，只改变页面中的部分内容。

**Ajax**就是实现局部刷新的一种技术，通过异步对象来实现。



## 1.2 异步请求对象

&emsp;异步对象(XmlHttpRequest，简称xhr)存在于内存中，它用于在后台与服务器交换数据；代替浏览器发起请求，并接收响应数据，来完成局部刷新。Ajax的核心对象就是这个XmlHttpRequest。

&emsp;现在的浏览器中都内建了XmlHttpRequest对象，在js中创建XmlHttpRequest对象的语法如下：

``` javascript
var xhr = new XmlHttpRequest();
```



## 1.3 Ajax

&emsp;**Ajax:** Asynchronous JavaScript and XML(异步的JavaScript和XML)。Ajax是一种局部刷新的方法，不是一种语言，它包含的技术主要有JavaScript、dom、css、XML等等。js负责创建异步对象、发送请求、更新页面的dom对象。xml是网络中传输的数据格式，因为比较冗余，现在都使用json代替xml了。

### 1.3.1 ajax异步实现步骤

1. 创建异步对象XmlHttpRequest

2. 给异步对象绑定事件。onreadstatechange：当异步对象发起请求获取了数据，都会触发这个事件；这个事件需要指定一个函数，在函数中处理状态的变化

   **onreadstatechange事件：**当请求被发送到服务器时，我们需要执行以下基于响应的任务。每当readState改变时，就会触发onreadstatechange事件。此事件可以指定一个处理函数function。通过判断xhr对象的状态，获取服务端返回的数据。

   **readState属性：** 表示异步对象的状态,0、1、2、3、4

   **status属性：** 表示网络请求的状态，200、404、500

3. 初始化异步请求对象。

   异步的方法open()。

   ```javascript
   xhr.open(请求方式GET|POST,"服务器端的访问地址",同步|异步请求(默认是true，表示异步));
   ```

4.  使用异步对象发送请求

   ``` javascript
   xhr.send();
   ```

   使用异步对象的属性responseText，获取服务器端返回的数据。

&emsp;**例子：**

``` javascript
var xhr = new XmlHttpRequest()
btn.onclick = fun(){
    alter("按钮点击");
}

xhr.onreadstatechange = function(){
    // 处理请求的状态变化
    /* 异步对象的readState属性表示异步对象请求的状态变化
    	0：创建异步对象时，new XmlHttpRequest
    	1：初始话异步请求对象，xhr.open()
    	2：发送请求，xhr.send();
    	3：从服务器端获取了数据,此时，3是异步对象内部使用，获取了原始的数据
    	4：一部对象吧接收的数据处理完成。开发人员在4的时候更新页面
    */
    if(xhr.readState==4 && xhr.status==200){
        // 可以处理服务器端的数据，更新当前页面
        var data = xhr.responseText();
        document.getElementById("name").value = data;
    }
}
```



## 1.4 Ajax实例

### 1.4.1 全局刷新计算BMI

需求：计算某个用户的BMI。用户在jsp输入自己的身高、体重，在servlet中计算bmi，并显示bmi的计算结果和建议。bmi=体重(kg)/身高^2(m)。

成人BMI的值：

1. 过轻：小于18.5
2. 正常：18.5-23.9
3. 过重：24-27
4. 肥胖：28-32
5. 非常肥胖：大于32

web.xml

```xml
  <servlet>
    <servlet-name>BmiServlet</servlet-name>
    <servlet-class>BmiServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>BmiServlet</servlet-name>
    <url-pattern>/bmiServlet</url-pattern>
  </servlet-mapping>
```

servlet

```java
public class BmiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求参数
        String strName = req.getParameter("name");
        String strHeight = req.getParameter("height");
        String strWeight = req.getParameter("weight");

        // 计算bmi
        float h = Float.valueOf(strHeight);
        float w = Float.valueOf(strWeight);
        float bmi = w / (h * h);
        String msg = "";
        if (bmi < 18.5) {
            msg = "您比较偏瘦";
        } else if (bmi >= 18.5 && bmi <= 23.9) {
            msg = "您的bmi是正常的";
        } else if (bmi >= 24 && bmi <= 27) {
            msg = "您有点壮实";
        } else if (bmi >= 28 && bmi <= 32) {
            msg = "有没有考虑减肥呢";
        } else if (bmi > 32) {
            msg = "您必须得减肥了";
        }
        msg = "您好" + strName + "您的bmi值是" + bmi + "," + msg;

        // 1. 使用新页面展示
        // 将数据存储到request
        req.setAttribute("msg", msg);
        // 转发到新的页面
        req.getRequestDispatcher("/result.html").forward(req, resp);
        
        // 2.使用HttpServletResponse输出数据
        resp.setContentType("text/html;charset=utf-8");
        // 获取PrintWriter
        PrintWriter pw = resp.getWriter();
        // 输出数据
        pw.println(msg);
        // 清空缓存
        pw.flush();
        // 释放资源
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

html/jsp

```html
<!DOCTYPE html>
<html lang="zn">
<head>
    <meta charset="UTF-8">
    <title>全局刷新</title>
</head>
<body>
    <P>全局刷新</P>
    <form action="bmiServlet" method="get">
        姓名:<input type="text" name="name"><br>
        体重(Kg):<input type="text" name="weight"><br>
        身高(M):<input type="text" name="height"><br>
        <input type="submit" value="提交"><br>
    </form>
</body>
</html>
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${msg}
</body>
</html>
```



### 1.4.2 Ajax局部刷新计算BMI

1. 创建html，使用XmlHttpRequest异步对象
   * 创建XmlHttpRequest对象
   * 绑定事件
2. 创建服务器servlet，接收请求、处理参数、响应数据

web.xml

```xml
  <!-- 局部刷新 -->
  <servlet>
    <servlet-name>AjaxBmiServlet</servlet-name>
    <servlet-class>AjaxBmiServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>AjaxBmiServlet</servlet-name>
    <url-pattern>/ajaxBmiServlet</url-pattern>
  </servlet-mapping>
```

AjaxBmiServlet.java

``` java
public class AjaxBmiServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求参数
        String strName = req.getParameter("name");
        String strHeight = req.getParameter("height");
        String strWeight = req.getParameter("weight");

        // 计算bmi
        float h = Float.valueOf(strHeight);
        float w = Float.valueOf(strWeight);
        float bmi = w / (h * h);
        String msg = "";
        if (bmi < 18.5) {
            msg = "您比较偏瘦";
        } else if (bmi >= 18.5 && bmi <= 23.9) {
            msg = "您的bmi是正常的";
        } else if (bmi >= 24 && bmi <= 27) {
            msg = "您有点壮实";
        } else if (bmi >= 28 && bmi <= 32) {
            msg = "有没有考虑减肥呢";
        } else if (bmi > 32) {
            msg = "您必须得减肥了";
        }
        msg = "您好" + strName + "您的bmi值是" + bmi + "," + msg;

        // 响应Ajax需要的数据，使用HttpResponse输出数据
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.println(msg);
        pw.flush();
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 可以在xhr.open()中改变请求方式为post，查看控制台是否输出
        System.out.println("使用Ajax发起了请求");
    }
}
```

AjaxBmi.html

``` html
<!DOCTYPE html>
<html lang="zn">
<head>
    <meta charset="UTF-8">
    <title>Ajax局部刷新计算bmi</title>
    <script>
        function doAjax() {
            // 1. 创建异步对象
            var xhr = new XMLHttpRequest();
            // 2. 绑定事件
            xhr.onreadystatechange = function () {
                // 处理服务器端返回的数据，更新当前页面
                if (xhr.readyState == 4 && xhr.status == 200) {
                    alert(xhr.responseText)
                    document.getElementById("resultP").innerHTML = xhr.responseText;
                }
            };
            // 3. 初始请求数据
            // 获取dom对象的value值
            var name = document.getElementById("name").value;
            var weight = document.getElementById("weight").value;
            var height = document.getElementById("height").value;
            var param = name + "&weight=" + weight + "&height=" + height
            alert(param)
            xhr.open("get", "ajaxBmiServlet?name=" + param, true)
            // 4. 发起请求
            xhr.send();
        }
    </script>
</head>
<body>
    <P>Ajax局部刷新</P>
    <div action="bmiServlet" method="get">
        姓名:<input type="text" id="name"><br>
        体重(Kg):<input type="text" id="weight"><br>
        身高(M):<input type="text" id="height"><br>
        <input type="button" value="计算bmi" onclick="doAjax()"><br>
        <p id="resultP"> bmi值：</p>
    </div>
    <!-- 没有使用form -->
</body>
</html>
```



### 1.4.3 案例-根据省份id查询省份名称

需求：用户在文本框输入省份的编号id，在其他文本框显示省份名称。

