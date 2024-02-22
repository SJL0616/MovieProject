package movie.frontcontroller;
public class ViewResolver {
   public static String makeView(String nextPage) {
	   return "/WEB-INF/movie/"+nextPage+".jsp";
   }
}
