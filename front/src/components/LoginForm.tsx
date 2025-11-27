import { useState } from "react";
import { useNavigate } from "react-router-dom";

function LoginForm() {
    const navigate = useNavigate();
    const [error, setError] = useState(null);

    function handleSubmit(event: any) {
        event.preventDefault();
        setError(null);
        fetch("http://localhost:8080/auth/login", {
            method: "POST",
            body: JSON.stringify({
                login: event.target[1].value,
                password: event.target[2].value
            }),
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then((res) => {
                if (res.ok) {
                    res.json().then((data) => {
                        // console.log(data.status);
                        sessionStorage.setItem("id", data.id);
                        sessionStorage.setItem("login", data.login);
                        sessionStorage.setItem("token", data.token);
                        sessionStorage.setItem("expiresIn", data.expiresIn);
                        navigate("/");
                    })
                }
                else {
                    if (res.status === 401) {
                        res.json().then((data) => {
                            setError(data.description);
                        })
                    }
                    else {
                        throw new Error("Erreur serveur");
                    }
                }
            })
            .catch((err) => {
                console.log("login catch")
                console.log(err.message)


                setError(err.message)
            })
    }

    return (
        <div className="responsive-form" onSubmit={handleSubmit}>
            <form action="" method="post">
                <input type="hidden"></input>

                <p>{error}</p>
                <label htmlFor="form-login" hidden></label>
                <input id="form-login" type="text" name="login" placeholder="Login" title="Login" autoComplete="username"
                    autoFocus required></input>

                <label htmlFor="form-password" hidden></label>
                <input id="form-password" type="password" name="password" placeholder="Password" title="Password"
                    autoComplete="new-password" required></input>

                <button type="submit" name="submit">Submit</button>
            </form>
        </div>
    );
}

export default LoginForm;