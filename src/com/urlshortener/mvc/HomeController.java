package com.urlshortener.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	//need a controller method to show the form
	@RequestMapping("/")
	public String showPage() {
		return "url-shortener-home";
	}

	//need a controller method to process the form
	@RequestMapping("/shortenURL")
	//@RequestMapping("/")
	public String shortenURL(HttpServletRequest request, Model model) {
		//read the request parameter from html form
		String longURL = request.getParameter("longURL");

		//shorten the url if url is valid else display invalid url
		String shortURL = Main.getShortURL(longURL);

		if (!shortURL.equals("Invalid URL"))
			shortURL = "The Shortened URL is: " + shortURL;

		//add shortened url to model
		model.addAttribute("shortURL", shortURL);
		model.addAttribute("longURL", longURL);

		return "forward:/";
	}

	@RequestMapping("/shortenURL/{baseURL}")
	public String redirect(@PathVariable String baseURL) {
		System.out.println(baseURL);
		String longURL = null;
		longURL = Main.getLongURL(baseURL);
		if (longURL == null)
			return "Page Not Found";
		return "redirect:" + longURL;
	}

}
