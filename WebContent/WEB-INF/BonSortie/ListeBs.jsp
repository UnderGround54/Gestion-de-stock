<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../Entete.jsp"%>
<body onload="valider(this)">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">
				<i class="fa fa-mail-reply"></i> BON DE SORTIE
			</h3>
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index"> Home </a></li>
				<li>_<i class="fa fa-list-alt"> </i> Liste Bon De Sortie</li>
			</ol>
		</div>
	</div>
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<div id="html">
			<button data-toggle="modal" onclick="ajouter(this)"
				data-backdrop="false" href="#formulaire" class="btn btn-lg btn-sm"
				style="background-color: #be72e9; color: white;">
				<div class="fa fa-plus"></div>
				AJOUTER
			</button>
		</div>
		<div class="col-lg-6">
			<div class="nav search-row">
				<form class="navbar-form" method="get" action="ControleBonSortie">
					<div class="input-group">
						<input class="form-control" value="" name="search"
							placeholder="Search" type="text" id="search">
						<button data-backdrop="false" value="search" name="resultat"
							class="btn btn-lg btn-sm"
							style="background-color: #be72e9; color: white;">
							<div class="fa fa-search"></div>
						</button>
					</div>
				</form>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="nav search-row" style="margin: 0% 0% 1% 28%;">
				<form class="navbar-form" method="get" action="ControleBonSortie">
					<div class="input-group">
						<input class="form-control" value="" name="date1"
							placeholder="Search" type="date" id="date1"> <input
							class="form-control" value="" name="date2" placeholder="Search"
							type="date" id="date2">
						<button data-backdrop="false" value="searchdate" name="resultat"
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
				value='<%String var = (String) request.getAttribute("trouve");
			if (var != null)
				out.println(var);%>'>
		</div>
		<div class="col-lg-12">
			<section class="panel">
				<header class="panel-heading"></header>
				<table class="table table-striped table-advance table-hover">
					<tbody>
						<tr>
							<th><div class="fa fa-bars"></div> Numéro Bs</th>
							<th><div class="fa fa-calendar"></div> Date</th>
							<th><div class="fa fa-plus"></div> Ligne</th>
							<th><div class="fa fa-eye"></div> Voir ligne</th>
							<th><div class="fa fa-cogs"></div> Action</th>
						</tr>
						<c:forEach var="bonsortie" items="${listebs}">
							<tr>
								<td>${bonsortie.getNumBs()}</td>
								<td>${bonsortie.getDateSortie()}</td>
								<td>
									<button data-toggle="modal" data-backdrop="false"
										href="#formulaireligne"
										class="btn btn-circle btn-sm fa fa-plus"
										style="background-color: gray; color: white;"></button>
								</td>
								<td><a href="ListeLigneBs"
									class="btn btn-sm btn-circle fa fa-eye"
									style="background-color: gray; color: white;"></a></td>
								<td>
									<button id="btn_modif" data-toggle="modal"
										data-backdrop="false"
										value="${bonsortie.getNumBs()}+${bonsortie.getDateSortie()}"
										href="#formulaire" class="btn btn-circle btn-sm fa fa-edit"
										onclick="modifier(this)"
										style="background-color: gray; color: white;"></button>

									<button style="background-color: gray; color: white;"
										value="${bonsortie.getNumBs()}" onclick="supprimer(this)"
										class="btn btn-circle btn-sm fa fa-trash-o"
										href="#formulaireConfirmation" data-toggle="modal"
										data-backdrop="false"></button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</div>
	</div>

	<!-- MODALE BEGIN  Ajout -->
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
								<form name="form" action="ControleBonSortie"
									class="form-validate form-horizontal" id="feedback_form"
									method="post">
									<div class="panel-body">
										<div class="form-group">
											<label for="" class="control-label">Numéro Bs :</label>
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-user"></i></span> <input type="text"
													name="numbs" id="numbs" value="" class="form-control ">
											</div>

											<label for="" class="control-label">Date Sortie :</label>
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-home"></i></span> <input type="date"
													name="datesortie" id="datesortie" value=""
													class="form-control">
											</div>

											<input type="hidden" id="add_edit" name="ajoutbs"
												value="addbs">
											<div class="modal-footer">
												<button type="submit" name="ajoutbs" id="button"
													class="btn btn-success" style="margin-top: 10px;">
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

										<label for="" class="control-label">Numéro Bs :</label> <select
											class="form-control" data-toggle="dropdown" id="numbs"
											name="numbs">
											<c:forEach var="bonsortie" items="${listebs}">
												<option value="${bonsortie.getNumBs()}">${bonsortie.getNumBs()}</option>
											</c:forEach>
										</select> <label for="" class="control-label">Numéro produit :</label>
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

										<input type="hidden" name="ajoutlignebs" value="addlignebs">
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
						<a id="supp" href="ControleBonSortie?resultat=supprbs&amp;numbs="
							class="btn btn-success btn-sm">Confirmer</a>
						<button class="btn btn-danger btn-sm" data-dismiss="modal">Annuler</button>
					</div>
				</div>
			</div>
		</div>
	</div>

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
</body>
<!-- MODALE END -->
<script type="text/javascript">
	function supprimer(champ) {
		$('#supp').attr('href',
				"ControleBonSortie?resultat=supprbs&numbs=" + champ.value);
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
		var currenDate = new Date();
		$("#numbs").val("BS_");
		document.getElementById("button").innerText = "Ajouter";
		//document.getElementById("numbs").value = "";
		document.getElementById("datesortie").value = currenDate.toISOString()
				.slice(0, 10);
		document.getElementById("add_edit").value = "addbs";
	}

	function modifier(champ) {
		var chaine = champ.value;
		var liste = chaine.split("+");
		document.getElementById("numbs").value = liste[0];
		$('#numbs')[0].readOnly = true;
		document.getElementById("datesortie").value = liste[1];
		document.getElementById("add_edit").value = "editbs";
		document.getElementById("button").innerText = "Modifier";
	}
</script>
<%@ include file="../Footer.jsp"%>