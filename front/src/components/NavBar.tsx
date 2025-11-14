function NavBar() {
  function testHandleClick() {
    fetch("http://localhost:8080/scores")
      .then((resp) => resp.json())
      .then((resp) => console.log(resp))
      .catch((e) => console.log("something went wrong: " + e))
  }


  return (
    <nav>
      <ul className="navbar">
        <li>Game</li>
        <li>Leaderboards</li>
        <li>Login</li>
        <li>Register</li>
        <li onClick={testHandleClick}>Test</li>
        <li className="right">Logout</li>
      </ul>
    </nav>
  );
}

export default NavBar;
