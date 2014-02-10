<div id="register" class="animate form">
            <form  action="/user/new_user" autocomplete="on" method="POST"> 
                <h1> Skapa konto </h1> 
                <p> 
                    <label for="firstName" class="uname" data-icon="u">Anv&auml;ndarnamn</label>
                    <input id="firstName" name="firstName" required="required" type="text" placeholder="mysuperusername690" />
                </p>
                <p> 
                    <label for="lastName" class="uname" data-icon="u">Anv&auml;ndarnamn</label>
                    <input id="lastName" name="lastName" required="required" type="text" placeholder="mysuperusername690" />
                </p>
                <p> 
                    <label for="username" class="uname" data-icon="u">Anv&auml;ndarnamn</label>
                    <input id="username" name="usernam" required="required" type="text" placeholder="mysuperusername690" />
                </p>
                <p> 
                    <label for="email" class="youmail" data-icon="e" > Email</label>
                    <input id="email" name="email" required="required" type="email" placeholder="mysupermail@mail.com"/> 
                </p>
                <p> 
                    <label for="password" class="youpasswd" data-icon="p">L&ouml;senord * </label>
                    <input id="password" name="password" required="required" type="password" placeholder="eg. X8df!90EO"/>
                </p>
                <p> 
                    <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Bekr&auml;fta l&ouml;senord * </label>
                    <input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required" type="password" placeholder="eg. X8df!90EO"/>
                </p>
                <p class="signin button"> 
                    <input type="submit" value="Skapa konto"/> 
                </p>
               
            </form>
        </div>