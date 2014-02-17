<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul>
	<li>
		<a class="menulink" href="shops">Butiker</a>
	</li>
	<li>
		<a class="menulink" href="suppliers">Leverantörer</a>
	</li>
	<li>
		<a class="menulink" href="categories">Varukategorier</a>
	</li>
	<li>
		<a class="menulink" href="help">Hjälp</a>
	</li>	
	<li>
		<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	</li>
</ul>
