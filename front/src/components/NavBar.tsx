import { NavLink } from "react-router-dom";

function NavBar() {
  // function testHandleClick() {
  //   fetch("http://localhost:8080/scores")
  //     .then((resp) => resp.json())
  //     .then((resp) => console.log(resp))
  //     .catch((e) => console.log("something went wrong: " + e))
  // }


  return (
    <nav>
      <ul className="navbar">
        <li><NavLink to="/">Home</NavLink></li>
        <li><NavLink to="/game">Game</NavLink></li>
        <li><NavLink to="/leaderboards">Leaderboards</NavLink></li>
        <li><NavLink to="/login">Login</NavLink></li>
        <li><NavLink to="/register">Register</NavLink></li>


        {/* <li onClick={testHandleClick}>Test</li> */}
        <li className="right">Logout</li>
      </ul>
    </nav>
  );
}

export default NavBar;
