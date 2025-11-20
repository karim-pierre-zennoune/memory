import { NavLink, useNavigate } from "react-router-dom";

function NavBar() {
  const navigate = useNavigate();

  function logout() {
    sessionStorage.clear();
    navigate("/");
  }


  return (
    <nav>
      <ul className="navbar">
        <li><NavLink to="/">Home</NavLink></li>
        <li><NavLink to="/game">Game</NavLink></li>
        <li><NavLink to="/leaderboards">Leaderboards</NavLink></li>
        {
          !sessionStorage.getItem("id") ?
            <> <li><NavLink to="/login">Login</NavLink></li>
              <li><NavLink to="/register">Register</NavLink></li>  </> : <></>
        }

        {
          sessionStorage.getItem("id") ?
            <>
              <li className="right" onClick={logout}>Logout [{sessionStorage.getItem("login")}]</li> </> : <></>
        }
      </ul>
    </nav>
  );
}

export default NavBar;
