<#macro login path>
    <form action="${path}" method="post">
        <div><label>Username: <input type="text" name="username"/></label></div>
        <div><label>Password: <input type="password" name="password"/></label></div>
        <div><input type="submit" value="Sign In"/></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>
<#macro logout>
    <form action="/logout" method="post">
        <input type="submit" value="Sign Out"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>

<#macro a path text>

    <a href="${path}">${text}</a>
</#macro>

<#macro div mes>
    <div>${mes}</div>
</#macro>