<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../Entete.jsp"%>
<body onload="valider(this)">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">
				<i class="fa fa-bookmark"></i> LIGNE DE BON DE SORTIE
			</h3>
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index"> Home </a></li>
				<li>_<i class="fa fa-list-alt"> </i> Liste Ligne De Bon De
					Sortie</li>
			</ol>
		</div>
	</div>
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<div class="col-lg-6">
			<div id="html">
				<button data-toggle="modal" onclick="ajouter(this)"
					data-backdrop="false" href="#formulaireligne"
					class="btn btn-lg btn-sm"
					style="background-color: #be72e9; color: white;">
					<div class="fa fa-plus"></div>
					AJOUTER
				</button>
			</div>
		</div>
		<div class="col-lg-6" style="margin: 0% 0% 1% 25%;">
			<div class="nav search-row">
				<form class="navbar-form" method="#" action="">
					<div class="input-group">
						<input class="form-control" value="" name="search"
							placeholder="Search" type="text" id="quick">
						<button data-backdrop="false" value="" name="resultat"
							class="btn btn-lg btn-sm"
							style="background-color: #be72e9; color: white;">
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
				value='<%String var = (String) request.getAttribute("stock");
			if (var != null)
				out.println(var);%>'>
		</div>
		<div class="col-lg-12">
			<section class="panel">
				<header class="panel-heading"></header>
				<table class="table table-striped table-advance table-hover">
					<thead>
						<tr>
							<th><div class="fa fa-bars"></div> Numéro Bs</th>
							<th><div class="fa fa-signal"></div> Produit</th>
							<th><div class="fa fa-mail-reply"></div> Qte Sortie</th>
							<th><div class="fa fa-cogs"></div> Action</th>
						</tr>
					</thead>
					<thead>
					</thead>
					<tbody>
						<c:forEach var="lignebs" items="${listelignebs}">
							<tr>
								<td>${lignebs.getNumBs()}</td>
								<td>${lignebs.getDesignProduit()}</td>
								<td>${lignebs.getQteSortie()}</td>
								<td>
									<button id="btn_modif" data-toggle="modal"
										data-backdrop="false"
										value="${lignebs.getIdLigneBs()}+${lignebs.getNumBs()}+${lignebs.getNumProduit()}+${lignebs.getQteSortie()}"
										href="#formulaireligne"
										class="btn btn-circle btn-sm fa fa-edit"
										onclick="modifier(this)"
										style="background-color: gray; color: white;"></button>

									<button
										value="${lignebs.getIdLigneBs()}+${lignebs.getNumProduit()}+${lignebs.getQteSortie()}"
										onclick="supprimer(this)"
										class="btn btn-circle btn-sm fa fa-trash-o"
										href="#formulaireConfirmation" data-toggle="modal"
										data-backdrop="false"
										style="background-color: gray; color: white;"></button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</div>
	</div>

	<!-- MODALE BEGIN  Ligne -->
	<div class="col-md-6 col-xs-12 col-md-offset-3">
		<div class="modal fade" id="formulaireligne">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							style="color: red;">x</button>
					</div>
					<div class="modal-body">
						<div class="panel-body">
							<div class="form">
								<form name="form" action="ControleLigneBs"
									class="form-validate form-horizontal" id="feedback_form"
									method="post">
									<div class="form-group">
										<input type="hidden" name="idlignebs" id="idlignebs" value=""
											class="form-control "> <label for=""
											class="control-label">Numéro Bs :</label> <select
											class="form-control" data-toggle="dropdown" id="numbs"
											name="numbs">
											<c:forEach var="bonsortie" items="${listebs}">
												<option value="${bonsortie.getNumBs()}">${bonsortie.getNumBs()}</option>
											</c:forEach>
										</select> <label for="" class="control-label">Numéro Produit :</label>
										<select class="form-control" data-toggle="dropdown"
											id="numproduit" name="numproduit">
											<c:forEach var="produit" items="${listeproduit}">
												<option value="${produit.getNumProduit()}">${produit.getDesign()}</option>
											</c:forEach>
										</select> <label for="" class="control-label">Qte Sortie :</label>
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-user"></i></span> <input type="text"
												name="qtesortie" id="qtesortie" value=""
												class="form-control ">
										</div>

										<input type="hidden" id="add_edit" name="ajoutlignebs"
											value="addlignebs">
										<button type="submit" name="ajoutlignebs" id="button"
											class="btn btn-success" style="margin-top: 10px;">
											<span class="fa fa-send">Ajouter</span>
										</button>

									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- MODALE END Ligne -->

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
							href="ControleLigneBs?resultat=suprrlignebs&amp;idlignebs=&amp;numproduit=&amp;qtesortie="
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
								<center>stock insuffisant</center>
							</h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function supprimer(champ) {
			var chaine = champ.value;
			var liste = chaine.split("+");
			$('#supp').attr(
					'href',
					"ControleLigneBs?resultat=suprrlignebs&idlignebs="
							+ liste[0] + "&numproduit=" + liste[1]
							+ "&qtesortie=" + liste[2]);
		}

		function valider() {
			var x = document.getElementById("trouve").value;
			if (x != "") {
				$(window).on('load', function() {
					$('#validation').modal('show');
				});
			}
		}

		function ajouter() {
			document.getElementById("button").innerText = "Ajouter";
			document.getElementById("idlignebs").value = "";
			document.getElementById("qtesortie").value = "";
			//document.getElementById("numbe").value = "";
			document.getElementById("add_edit").value = "addlignebs";
		}

		function modifier(champ) {

			var chaine = champ.value;
			var liste = chaine.split("+");
			document.getElementById("idlignebs").value = liste[0];
			document.getElementById("numbs").value = liste[1];
			document.getElementById("numproduit").value = liste[2];
			document.getElementById("qtesortie").value = liste[3];
			document.getElementById("add_edit").value = "modiflignebs";
			document.getElementById("button").innerText = "Modifier";
		}
	</script>

	<%@ include file="../Footer.jsp"%></body>