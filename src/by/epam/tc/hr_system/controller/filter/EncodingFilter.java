package by.epam.tc.hr_system.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
/**
 * Filter encodes all data to UTF-8 from ServletRequest request
 * 
 * @author Ivan Chernikau
 *
 */
 
@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class EncodingFilter implements Filter {

	    private static final String ENCODING = "encoding";
	    private String code;


	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	        code = filterConfig.getInitParameter(ENCODING);
	    }

	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
	        String codeRequest = request.getCharacterEncoding();
	        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
	            request.setCharacterEncoding(code);
	        }
	        filterChain.doFilter(request, response);
	    }


	    @Override
	    public void destroy() {
	        code = null;
	    }

}
