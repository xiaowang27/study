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