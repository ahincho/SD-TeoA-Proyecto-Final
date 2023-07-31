import './Login.css'

function Login({ bankName, apiUrl }) {
	const handleLogin = async (e) => {
		e.preventDefault();
		try {
			const email = document.getElementById('floatingInputEmail').value;
			const password = document.getElementById('floatingPassword').value;
			const response = await fetch(`${apiUrl}/login`, {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ email, password }),
			});
			if (!response.ok) {
				throw new Error('Login failed. Please check your credentials.');
			}
			const data = await response.json();
			window.location.href = `/${data.document}/accounts`;
		} catch (error) {
			console.error('Error during login: ', error);
			alert('Login failed. Please check your credentials.');
		}
	};
	return (
		<div className="container d-flex align-items-center justify-content-center vh-100">
			<div className="container-xxl">
				<div className="row-xxl">
					<div className="col-lg-10 col-xl-9 mx-auto">
						<div className="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden">
							<div className="card-img-left d-none d-md-flex">
								{/* Background image for card set in CSS! */}
							</div>
							<div className="card-body p-4 p-sm-5">
								<h5 className="card-title text-center mb-5 fw-light fs-5"><strong>{bankName} Bank - Login</strong></h5>
								<form onSubmit={handleLogin}>
									<div className="form-floating mb-3">
										<input type="email" className="form-control" id="floatingInputEmail" placeholder="name@example.com" required autoFocus />
										<label htmlFor="floatingInputEmail">Email Address</label>
									</div>
									<div className="form-floating mb-3">
										<input type="password" className="form-control" id="floatingPassword" placeholder="Password" required autoFocus />
										<label htmlFor="floatingPassword">Password</label>
									</div>
									<div className="d-grid mb-2">
										<button className="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit">Login</button>
									</div>
								</form>
								<hr className="my-4" />
								<div className="d-grid mb-2">
									<button className="btn btn-lg btn-google btn-login fw-bold text-uppercase" type="submit">
										<i className="fab fa-google me-2"></i> Login with Google
									</button>
								</div>
								<div className="d-grid">
									<button className="btn btn-lg btn-facebook btn-login fw-bold text-uppercase" type="submit">
										<i className="fab fa-facebook-f me-2"></i> Login with Facebook
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}

export default Login;