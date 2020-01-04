<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Ricerca Tutor</title>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
		
	<div class="content text-center" style="width: 65%;margin: 0 auto; border: 2px solid #232F3E; border-radius:8px;">
		<div>
			<h2>Ricerca Tutor</h2>
		</div>
		
		<div style="margin: 0 auto;">  
		   	<form action="">
		   		<div class="row d-flex justify-content-center" >
		            <div class="form-group col-lg-8 col-md-8 col-sm-6 col-xs-6">
		                <label for="startActivity" class="control-label">Selezionare la data di inizio ricerca</label>
		                <input type="date" class="form-control" id="startActivity" name="startActivity">
		            </div>
		        </div>
		        
		        <div class="row d-flex justify-content-center">
		        	<div class="form-group col-lg-8 col-md-8 col-sm-6 col-xs-6">
		                <label for="finishActivity" class="control-label">Selezionare la data di inizio ricerca</label>
		                <input type="date" class="form-control" id="finishActivity" name="finishActivity">
		            </div>
		        </div>
		        
		        <div >		        	
		        	<button type="submit" class="btn btn-primary">Cerca</button>
		        </div>
		    </form>
		</div>
	</div>
		    
    <%@ include file="/partials/footer.jsp" %>
    
    <script type="text/javascript">
		$(document).ready( function() {
			document.getElementById("startActivity").valueAsDate = new Date();
			document.getElementById("finishActivity").valueAsDate = new Date();
		});
	</script>
</body>
</html>