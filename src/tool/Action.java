package tool;

import java.servlet.http.HttpServletRequest;
import java.servlet.http.HttpServletResponse;

public abstract class Action{
	public abstract void execute(HttpServletRequest request, HttpServletResponse respons) throws Exception;
}
}