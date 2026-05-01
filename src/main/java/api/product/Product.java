package api.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Product
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/product" })
@jakarta.servlet.annotation.MultipartConfig
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String, String> map = new HashMap<>();
	
	public Product() {
		map.put("강호동", "Jsp");
		map.put("유재석", "Node");
		map.put("신동엽", "Java");
		map.put("서장훈", "Spring");
		map.put("아이유", "Jquery");		
	}
       
	// 회원 조회
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
								 throws ServletException, IOException {
		
		// http://localhost:8080/product?name=강호동
		request.setCharacterEncoding("utf-8"); // 구버전은 톰캣 버전이 낮기 때문에 추가해야 함
		String name = request.getParameter("name");
		
		// String name     = "강호동";
		String language = map.get(name);
		
		// 출력
		// response.setContentType("text/html; charset=utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String json     = """
				{
					"name"     : "%s",
					"language" : "%s"
				}
				""".formatted(name, language); // jdk17
		
		out.print(json);
		out.flush();
		out.close();
	}
	
	// 회원 등록
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String name     = request.getParameter("name");
		String language = request.getParameter("language");
		
		map.put(name, language);
		
		System.out.println(name + "이(가) 추가되었습니다.");
		
	}

}
