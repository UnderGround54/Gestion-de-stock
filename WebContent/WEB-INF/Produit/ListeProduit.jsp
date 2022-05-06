<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../Entete.jsp"%>
<body onload="valider(this)">
<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="fa fa-shopping-cart"></i> ETAT DE STOCK
		</h3>
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index"> Home </a></li>
			<li>_<i class="fa fa-list-alt"> </i> Liste DE Produit</li>
		</ol>
	</div>
</div>
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<div id="html">
		<button data-toggle="modal" onclick="ajouter(this)"
			data-backdrop="false" href="#formulaire"
			class="btn btn-lg btn-sm" style="background-color: #be72e9; color : white;">
			<div class="fa fa-plus"></div>
			AJOUTER
		</button>
	</div>
	<div class="col-lg-6" style="margin: 0% 0% 1% 68%;">
		<div class="nav search-row">
			<form class="navbar-form" method="#" action="">
				<div class="input-group">
					<input class="form-control" value="" name="search"
						placeholder="Search" type="text" id="quick">
					<button data-backdrop="false" value="" name="resultat"
						class="btn btn-lg btn-sm" style="background-color: #be72e9; color : white;">
						<div class="fa fa-search"></div>
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="row">
	<div>
		<input type="hidden" id="trouve"
			value='<%String var = (String) request.getAttribute("trouve");
			if (var != null)
				out.println(var);%>'>
	</div>
	<div>
			<input type="hidden" id="stockmodif"
				value='<%String stock = (String) request.getAttribute("stock");
			if (stock != null)
				out.println(stock);%>'>
		</div>
	<div class="col-lg-12">
		<section class="panel">
			<header class="panel-heading"></header>
			<table class="table table-striped table-advance table-hover">
				<thead>
					<tr>
						<th><div class="fa fa-bars"></div> Numéro Produit</th>
						<th><div class="fa fa-bookmark"></div> Designation</th>
						<th><div class="fa fa-signal"></div> Stock</th>
						<th><div class="fa fa-cogs"></div> Action</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="produit" items="${listeproduit}">
						<tr>
							<td>${produit.getNumProduit()}</td>
							<td>${produit.getDesign()}</td>
							<td>${produit.getStock()}</td>
							<td>
								 

								<button value="${produit.getNumProduit()}"
									onclick="supprimer(this)"
									class="btn btn-circle btn-sm fa fa-trash-o"
									href="#formulaireConfirmation" data-toggle="modal"
									data-backdrop="false" style="background-color: gray ;color : white;"></button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</div>
</div>

<div class="col-md-6 col-xs-12 col-md-offset-3">
	<div class="modal fade" id="formulaire">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						style="color: red;">x</button>
				</div>
				<div class="modal-body">
					<div class="panel-body">
						<div class="form">
							<form name="f" action="ControleProduit"
								class="form-validate form-horizontal" id="feedback_form"
								method="post">
								<div class="panel-body">
									<div class="form-group">
										<label for="" class="control-label">Numéro Produit :</label>
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-user"></i></span> <input type="text"
												name="numproduit" style="text-transform: uppercase;"
												required="" onblur="IsExisteDeja(this)" id="numproduit"
												value="" class="form-control ">
										</div>

										<label for="" class="control-label">Designation :</label>
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-home"></i></span> <input type="text"
												name="design" id="design" required="" value=""
												class="form-control">
										</div>

										<label for="" class="control-label">Stock :</label>
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-home"></i></span> <input type="text"
												name="stock" id="stock" required="" value=""
												class="form-control">
										</div>

										<input type="hidden" id="add_edit" name="ajoutproduit"
											value="addproduit">
										<div class="modal-footer">
											<button type="submit"
												name="ajoutproduit" id="button" class="btn btn-success"
												style="margin-top: 10px;">
												<span class="fa fa-send"></span>
											</button>
										</div>

									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- MODALE END ajout -->

<!-- MODALE BEGIN SUPPRESSION -->
<div class="col-md-3 col-xs-6 col-md-offset-3">
	<div class="modal fade" id="formulaireConfirmation">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						style="color: red;">x</button>
				</div>
				<div class="modal-body">
					<div class="panel-body">
						<h3>Voulez-vous vraiment supprimer?</h3>
					</div>
				</div>

				<div class="modal-footer">
					<a id="supp"
						href="ControleProduit?resultat=supprproduit&amp;numproduit="
						class="btn btn-success btn-sm">Confirmer</a>
					<button class="btn btn-danger btn-sm" data-dismiss="modal">Annuler</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- MODALE END -->

<div class="col-md-3 col-xs-6 col-md-offset-3">
	<div class="modal fade" id="validation">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						style="color: red;">x</button>
				</div>
				<div class="modal-body">
					<div class="panel-body">
						<h3>
							<center>Numéro déjà existe</center>
						</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function supprimer(champ) {
		$('#supp').attr(
				'href',
				"ControleProduit?resultat=supprproduit&numproduit="
						+ champ.value);
	}

	function ajouter() {
		document.getElementById("button").innerText = "Ajouter";
		document.getElementById("numproduit").value = "PRO_";
		document.getElementById("design").value = "";
		document.getElementById("stock").value = "";
		document.getElementById("add_edit").value = "addproduit";
	}

	function modifier(champ) {
		var chaine = champ.value;
		var liste = chaine.split("+");
		
		document.getElementById("numproduit").value = liste[0];
		$('#numproduit')[0].readOnly = true;
		document.getElementById("design").value = liste[1];
		document.getElementById("stock").value = liste[2];
		document.getElementById("add_edit").value = "editproduit";
		document.getElementById("button").innerText = "Modifier";
	}
	
	function valider() {
		var x = document.getElementById("trouve").value;
		if (x != "") {
			$(window).on('load',function(){
				$('#validation').modal('show');
			});
		}
	}
</script>
<script type="text/javascript">
$("#feedback_form").validate({
        rules: {
          numproduit : {
            required : true;
          },
          design : {
            required : true;
          },
          stock : {
              required :true;
          }
	},
	});
	</script>
<%@ include file="../Footer.jsp"%>