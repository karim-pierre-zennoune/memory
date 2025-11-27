import { useState } from "react";
import { useNavigate } from "react-router-dom";

function RegisterForm() {
    const navigate = useNavigate();
    const [error, setError] = useState("");

    function handleSubmit(event: any) {
        event.preventDefault();
        setError("");
        if (event.target[2].value !== event.target[3].value) {
            setError("Password don't match");
            return;
        }
        fetch("http://localhost:8080/auth/signup", {
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
                    navigate("/login")
                }
                else {
                    throw new Error("Erreur serveur //TODO");
                }
            })

            .catch((err) => setError(err.message))
    }

    return (
        <div className="responsive-form" onSubmit={handleSubmit}>
            <form action="">

                <input type="hidden" />
                <p>{error}</p>
                <label htmlFor="form-login" hidden></label>
                <input id="form-login" type="text" name="login" placeholder="Login" title="Login" autoComplete="username"
                    autoFocus required></input>


                <label htmlFor="form-password" hidden></label>
                <input id="form-password" type="password" name="password" placeholder="Password" title="Password"
                    autoComplete="new-password" required></input>

                <label htmlFor="form-password-confirm"></label>
                <input id="form-password-confirm" type="password" name="password-confirm" placeholder="Confirm Password"
                    autoComplete="new-password" title="New Password" required></input>

                <button type="submit" name="submit" value="Envoyer">Submit</button>
            </form>

        </div>
    );
}

export default RegisterForm;