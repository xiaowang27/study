import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
