<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Novo Ativo</title>
	</head>
	<body>
	
		<form method="POST" action="registraProduto">
			<p>
				Consumo Energia: <input type="valor" name="conusmoEnergia" /><form:errors path="ativo.consumo" />
			</p>
			<p>
				<input type="submit" value="Calcular" />
			</p>						
		</form>
		
	</body>
</html> 