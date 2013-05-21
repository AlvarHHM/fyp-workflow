<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Form Builder</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/libs/jquery-1.8.2/jquery.js"></script>
<script type="text/javascript" src="js/libs/jqueryui-1.9.2/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/master.css">
<link rel="stylesheet" type="text/css" href="css/jqueryui/jquery-ui-1.9.2.custom.min.css">
<link rel="stylesheet" type="text/css" href="css/formBuilder.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link href="path/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="js/formBuilder/drag-and-drop.js"></script>
<script type="text/javascript" src="js/formBuilder/select-and-property.js"></script>
<script type="text/javascript" src="js/formBuilder/element-validation.js"></script>
<script type="text/javascript" src="js/formBuilder/local-validation.js"></script>
<script type="text/javascript" src="js/formBuilder/save-and-load.js"></script>
<script type="text/javascript">
            $(function() {
				$("#formDesc").val("");
			
                $("#formBuilder-leftPanel").tabs({
                    activate: function(event, ui) {
                        if (ui.newPanel.attr("id")==="tab-components")
                            $("#tab-hint").html("<span>Drag to add component to the form.</span>");
                        else
                            $("#tab-hint").html("");
                    }
                });
				
				//$(".date-picker").datepicker();
            });//
            
			function addAR(btn){
                $(btn).parent().parent().append("<li>"+$(btn).parent().html()+"</li>");
            }
            function removeAR(btn){
                if($(btn).parent().parent().children().length>1){
                    $(btn).parent().remove();
                }
            }
			function goBack()
			{
				if(confirm('Confirm?'))
					window.history.go(-1)
			}
			
			function post_to_url(path, params, method) {
				method = method || "post"; 

				var form = document.createElement("form");
				form.setAttribute("method", method);
				form.setAttribute("action", path);

				for(var key in params) {
					if(params.hasOwnProperty(key)) {
						var hiddenField = document.createElement("input");
						hiddenField.setAttribute("type", "hidden");
						hiddenField.setAttribute("name", key);
						hiddenField.setAttribute("value", params[key]);

						form.appendChild(hiddenField);
					 }
				}

				document.body.appendChild(form);
				form.submit();
			}
        </script>
</head>
<body>
	<div id="bodyContainer">
		<div id="topContainer">
			<%@ include file="head.jsp"%>
		</div>
		<div id="mainContainer">
			<div id="menu">
				<%@ include file="menu.jsp"%>
			</div>
			<div id="mainBody">
				<div id="page">
					<div id="content">
						<div id="formBuilder-container">
							<div id="form-header">
								<span id="form-title-label">Title: </span><span id="form-title">New Form</span>
								<div id="form-save-container">
									<input type="button" value="Back" onclick="goBack()">

									<button id="form-save">Save</button>
								</div>
							</div>
							<div id="formBuilder-leftPanel">
								<ul>
									<li><a href="#tab-components">Components</a></li>
									<li><a href="#tab-properties">Properties</a></li>
									<li><a href="#tab-formOptions">Form Options</a></li>
								</ul>
								<div id="tab-hint">
									<span>Drag to add component to the form.</span>
								</div>
								<div id="tab-components">
									<div class="formBuilder-component-container"
										id="component-HEADING">
										<div class="overlay"></div>
										<img class="formBuilder-component-icon" src="img/Heading.jpg" />
										<span class="formBuilder-component-name">Heading</span>
									</div>
									<div class="formBuilder-component-container"
										id="component-LABEL">
										<div class="overlay"></div>
										<img class="formBuilder-component-icon" src="img/Label.jpg" />
										<span class="formBuilder-component-name">Label</span>
									</div>
									<div class="formBuilder-component-container"
										id="component-TEXTFIELD">
										<div class="overlay"></div>
										<img class="formBuilder-component-icon" src="img/Textbox.jpg" />
										<span class="formBuilder-component-name">Textbox</span>
									</div>
									<div class="formBuilder-component-container"
										id="component-RADIOBUTTON">
										<div class="overlay"></div>
										<img class="formBuilder-component-icon" src="img/Radio.jpg" />
										<span class="formBuilder-component-name">Radio Group</span>
									</div>
									<div class="formBuilder-component-container"
										id="component-CHECKBOX">
										<div class="overlay"></div>
										<img class="formBuilder-component-icon" src="img/checkbox.jpg" />
										<span class="formBuilder-component-name">Checkbox</span>
									</div>
									<div class="formBuilder-component-container"
										id="component-COMBOBOX">
										<div class="overlay"></div>
										<img class="formBuilder-component-icon" src="img/combo.jpg" />
										<span class="formBuilder-component-name">Combobox</span>
									</div>
									<div class="formBuilder-component-container"
										id="component-TEXTAREA">
										<div class="overlay"></div>
										<img class="formBuilder-component-icon" src="img/textarea.jpg" />
										<span class="formBuilder-component-name">Textarea</span>
									</div>
									<div class="formBuilder-component-container"
										id="component-DATE">
										<div class="overlay"></div>
										<img class="formBuilder-component-icon"
											src="img/DatePicker.jpg" /> <span
											class="formBuilder-component-name">Date Picker</span>
									</div>
									<div class="formBuilder-component-container"
										id="component-UPLOAD">
										<div class="overlay"></div>
										<img class="formBuilder-component-icon" src="img/Upload.jpg" />
										<span class="formBuilder-component-name">Upload</span>
									</div>
								</div>
								<div id="tab-properties">
									<div class="formItem-properties-group" id="item-general">
										<div class="form-item-property" id="X">
											<span>X</span><input type="text" />
										</div>
										<div class="form-item-property" id="Y">
											<span>Y</span><input type="text" />
										</div>
										<div class="form-item-property" id="item-label">
											<span>Text</span><input type="text" /> <input
												type="checkbox" checked />
										</div>
									</div>
									<div class="formItem-properties-group" id="item-text-size">
										<hr />
										<span>Range</span>
										<div id="size-min">
											<span>Min</span> <input type="text" value="0" />
										</div>
										<div id="size-max">
											<span>Max</span> <input type="text" />
										</div>
										<div id="size-format">
											<span>Format</span> <select>
												<option>Characters</option>
											</select>
										</div>
										<div id="item-text-defaultValue">
											<label>Default Value</label> <input type="text" />
										</div>
									</div>
									<div class="formItem-properties-group" id="radio-list">
										<hr />
										<label>Radio group</label>
										<ul id="radio-choices">
											<li class="sample-choice">
												<!--<input type="radio" />-->
												<input class="choice" type="text" />
												<button class="add">+</button>
												<button class="remove">-</button></li>
										</ul>
									</div>
									<div class="formItem-properties-group" id="checkbox-list">
										<hr />
										<label>Checkbox group</label>
										<ul id="checkbox-choices">
											<li class="sample-choice">
												<!--<input type="checkbox" />-->
												<input class="choice" type="text" />
												<button class="add">+</button>
												<button class="remove">-</button></li>
										</ul>
									</div>
									<div class="formItem-properties-group" id="combobox-list">
										<hr />
										<label>Combo Box</label>
										<ul id="combobox-choices">
											<li class="sample-choice">
												<input class="choice" type="text" />
												<button class="add">+</button>
												<button class="remove">-</button></li>
										</ul>
									</div>
									<div class="formItem-properties-group" id="item-boxLayout">
										<hr />
										<span>Layout</span> <br /> <select>
											<option value="1">One</option>
											<option value="2">Two</option>
											<option value="3">Three</option>
											<option value="4">Four</option>
										</select> <span>Column</span>
									</div>
									<div class="formItem-properties-group" id="css">
										<hr />
										<div id="text-css">
											<label>Text CSS Rule</label>
											<div>
												<textarea class="itemCSS"></textarea>
												<button>Set CSS</button>
											</div>
										</div>
										<div id="choice-css">
											<label>Choice CSS Rule</label>
											<div>
												<textarea class="itemCSS"></textarea>
												<button>Set CSS</button>
											</div>
										</div>
										<div id="choice-box-css">
											<label>Checkbox/Radio CSS Rule</label>
											<div>
												<textarea class="itemCSS"></textarea>
												<button>Set CSS</button>
											</div>
										</div>
										<div id="choice-label-css">
											<label>Choice label CSS Rule</label>
											<div>
												<textarea class="itemCSS"></textarea>
												<button>Set CSS</button>
											</div>
										</div>
										<div id="textbox-css">
											<label>Textbox CSS Rule</label>
											<div>
												<textarea class="itemCSS"></textarea>
												<button>Set CSS</button>
											</div>
										</div>
										<div id="button-css">
											<label>Button CSS Rule</label>
											<div>
												<textarea class="itemCSS"></textarea>
												<button>Set CSS</button>
											</div>
										</div>
										<div id="progress-css">
											<label>Progress Bar CSS Rule</label>
											<div>
												<textarea class="itemCSS"></textarea>
												<button>Set CSS</button>
											</div>
										</div>
									</div>
									<div class="formItem-properties-group" id="options">
										<hr />
										<fieldset>
											<legend>Option</legend>
											<div id="item-required">
												<input type="checkbox" /><label>Required</label>
											</div>
										</fieldset>
									</div>
									<div class="formItem-properties-group" id="validation">
                                            <hr/>
                                            <span>Validation</span>
                                            <br/>
                                            <ul>
                                                <li id="Local-Validation">
                                                    <div>
                                                        <ul>
                                                            <li>
																
															</li>
                                                        </ul>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
									<!--
                                        <div class="formItem-properties-group" id="validation">
                                            <hr/>
                                            <span>Validation</span>
                                            <br/>
                                            <ul>
                                                <li id="form-databaseValidation">
                                                    <div>
                                                        <ul>
                                                            <li id="li-validation-database">
                                                                <select id="validation-db">
                                                                    <option>Database</option>
                                                                </select>
                                                            </li>
                                                            <li  id="li-validation-table">
                                                                <select id="validation-table">
                                                                    <option>Table</option>
                                                                </select>
                                                            </li>
                                                            <li  id="li-validation-field">
                                                                <select id="validation-field">
                                                                    <option>Field</option>
                                                                    <option>Count</option>
                                                                </select>
                                                            </li>
                                                            <li id="li-validation-operator">
                                                                <select id="validation-operator">
                                                                    <option>Operation</option>
                                                                    <option value=">">Greater than</option>
                                                                    <option value=">=">Greater than or Equal</option>
                                                                    <option value="<">Less than</option>
                                                                    <option value="<=">Less than or Equal</option>
                                                                    <option value="==">Equal</option>
                                                                    <option value="!=">Not Equal</option>
                                                                </select>
                                                            </li>
                                                            <li id="li-validation-value">
                                                                <label>Value </label><input id="validation-value" type="text"/>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
										-->
								</div>
								<div id="tab-formOptions">
									<ul>
										<li><label>Form ID</label>
											<div>
												<input id="formId" type="text" />
											</div></li>
										<li><label>Version</label>
											<div>
												<input id="formVersion" type="text" />
											</div></li>
										<li><label>Form Name</label>
											<div>
												<input id="formName" type="text" />
											</div></li>
										<li><label>Description</label>
											<div>
												<textarea id="formDesc"></textarea>
											</div></li>
										<!--
										<li>
											<fieldset>
												<legend>Access Right</legend>
												<div>
													<ul style="list-style: none;">
														<li>
															<div>
																<div>
																	<label>Department</label> <select id="formARDept">
																		<option>IT</option>
																	</select>
																</div>
																<div>
																	<label>Super Level</label> <select id="formARLv">
																		<option>600</option>
																	</select>
																</div>
																<input type="button" onclick="addAR(this)" value="+" />
																<input type="button" onclick="removeAR(this)" value="-" />
															</div>
														</li>
													</ul>
												</div>
											</fieldset>
										</li>
										-->
									</ul>
								</div>
							</div>
							<div id="formBuilder-stage"></div>
						</div>
					</div>
					<div id="footer"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
