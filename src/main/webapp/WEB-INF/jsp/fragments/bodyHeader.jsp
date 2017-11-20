<!--
    Copyright 2002-2013 the original author or authors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
-->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<nav class="navbar is-transparent">
	<div class="navbar-brand">
		<a class="navbar-item"
			href="<spring:url value="/" htmlEscape="true" />"> <img
			src="https://bulma.io/images/bulma-logo.png"
			alt="Bulma: a modern CSS framework based on Flexbox" width="112"
			height="28">
		</a> <a class="navbar-item is-hidden-desktop"
			href="https://github.com/jgthms/bulma" target="_blank"> <span
			class="icon" style="color: #333;"> <i
				class="fa fa-lg fa-github"></i>
		</span>
		</a> <a class="navbar-item is-hidden-desktop"
			href="https://twitter.com/jgthms" target="_blank"> <span
			class="icon" style="color: #55acee;"> <i
				class="fa fa-lg fa-twitter"></i>
		</span>
		</a>
		<div class="navbar-burger burger"
			data-target="navMenuTransparentExample">
			<span></span> <span></span> <span></span>
		</div>
	</div>
	<div id="navMenuTransparentExample" class="navbar-menu">
		<div class="navbar-start">
			<div class="navbar-item has-dropdown is-hoverable">
				<div class="navbar-link">More</div>
				<div id="moreDropdown" class="navbar-dropdown is-boxed">
					<a class="navbar-item " href="<spring:url value="/oups.html" htmlEscape="true" />">
					<p>
						<strong>Some Error!5!</strong> <br> <small>A tiny npm
							package to get started</small>
					</p>
					</a>
					<hr class="navbar-divider">
					<a class="navbar-item " href="https://bulma.io/made-with-bulma/">
						<p>
							<strong>Made with Bulma</strong> <br> <small>The
								official community badge</small>
						</p>
					</a>
					<hr class="navbar-divider">
					<a class="navbar-item " href="https://bulma.io/extensions/">
						<p>
							<strong>Extensions</strong> <br> <small>Side
								projects to enhance Bulma</small>
						</p>
					</a>
				</div>
			</div>
			<a class="navbar-item "
				href="<spring:url value="/owners/find.html" htmlEscape="true" />">
				<span class="bd-emoji">⭐️</span> Find Owners
			</a> <a class="navbar-item "
				href="<spring:url value="/users/find.html" htmlEscape="true" />">
				<span class="bd-emoji">❤️</span> Find Users
			</a> <a class="navbar-item "
				href="<spring:url value="/users/all.html" htmlEscape="true" />">
				<span class="bd-emoji">⭐️</span> All Users
			</a> <a class="navbar-item "
				href="<spring:url value="/vets.html" htmlEscape="true" />"> <span
				class="bd-emoji">❤️</span> Veterinarians
			</a>
		</div>
		<div class="navbar-end">
			<a class="navbar-item is-hidden-desktop-only"
				href="https://github.com/jgthms/bulma" target="_blank"> <span
				class="icon" style="color: #333;"> <i
					class="fa fa-lg fa-github"></i>
			</span>
			</a> <a class="navbar-item is-hidden-desktop-only"
				href="https://twitter.com/jgthms" target="_blank"> <span
				class="icon" style="color: #55acee;"> <i
					class="fa fa-lg fa-twitter"></i>
			</span>
			</a>
			<div class="navbar-item">
				<div class="field is-grouped">
					<p class="control">
						<a class="bd-tw-button button" href="/login"> <span
							class="icon"> <i class="fa fa-sign-in"></i>
						</span> <span> Login </span>
						</a>
					</p>
					<p class="control">
						<a class="button is-primary" href="<spring:url value="/users/new" htmlEscape="true"/>"> <span
							class="icon"> <i class="fa fa-user-circle"></i>
						</span> <span>Register</span>
						</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</nav>

