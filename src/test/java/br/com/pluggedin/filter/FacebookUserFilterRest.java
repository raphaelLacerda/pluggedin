package br.com.pluggedin.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Page;
import com.restfb.types.User;

/**
 * The Facebook User Filter ensures that a Facebook client that pertains to
 * the logged in user is available in the session object named "facebook.user.client".
 * 
 * The session ID is stored as "facebook.user.session". It's important to get
 * the session ID only when the application actually needs it. The user has to 
 * authorise to give the application a session key.
 * 
 * @author Dave
 */

@WebFilter("/facebook")
public class FacebookUserFilterRest implements Filter {

	private static final String	MY_ACCESS_TOKEN	= "AAACEdEose0cBAKGYM6DMo6ZCNYOGPDVmbUx7hL8whYber18l7fS0ZCVqwtZAX6fXoYca4gnmEHVYIMQgryL0zqCXmBoOIwjWPhfm2uEY528LFBBZCrfZC";

	private String				api_key;
	private String				secret;

	public void init(FilterConfig filterConfig) throws ServletException {

		api_key = filterConfig.getServletContext().getInitParameter("facebook_api_key");
		secret = filterConfig.getServletContext().getInitParameter("facebook_secret");

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN);

		List<AccessToken> tokens = facebookClient.convertSessionKeysToAccessTokens(api_key, secret, "sessionKey1", "sessionKey2");

		User user = facebookClient.fetchObject("me", User.class);
		Page page = facebookClient.fetchObject("cocacola", Page.class);

		System.out.println("User name: " + user.getName());
		System.out.println("Page likes: " + page.getLikes());

		System.out.println(tokens.get(0).getAccessToken());

		String query = "SELECT name FROM user WHERE uid=100002556737230";

		List<User> users = facebookClient.executeQuery(query, User.class);

		System.out.println(users);

	}

}