import './Register.css'

function Register({ bankName, apiUrl }) {
	const handleSubmit = async (event) => {
		event.preventDefault();
		try {
			const formData = {
				email: document.getElementById('floatingInputEmail').value,
				password: document.getElementById('floatingPassword').value,
				document: document.getElementById('floatingDocument').value,
				firstname: document.getElementById('floatingFirstname').value,
				lastname: document.getElementById('floatingLastname').value,
			};
			const response = await fetch(apiUrl, {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify(formData),
			});
			if (!response.ok) {
				throw new Error('Failed to register.');
			}
			console.log('Registration successful.');
			window.location.href = `/${bankName}/login`;
		} catch (error) {
			console.error('Error registering: ', error);
			alert('Registration failed. Please try again later.');
		}
	};
	return (
		<div className="container-fluid">
			<div className="row">
				<div className="col-lg-10 col-xl-9 mx-auto">
					<div className="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden">
						<div className="card-img-left d-none d-md-flex">
							{/* Background image for card set in CSS! */}
						</div>
						<div className="card-body p-4 p-sm-5" onSubmit={handleSubmit}>
							<h5 className="card-title text-center mb-5 fw-light fs-5"><strong>{bankName} Bank - Register</strong></h5>
							<form>
								<div className="form-floating mb-3">
									<input type="email" className="form-control" id="floatingInputEmail" placeholder="name@example.com" required autoFocus />
									<label htmlFor="floatingInputEmail">Email Address</label>
								</div>
								<div className="form-floating mb-3">
									<input type="password" className="form-control" id="floatingPassword" placeholder="Password" required autoFocus />
									<label htmlFor="floatingPassword">Password</label>
								</div>
								<div className="form-floating mb-3">
									<input type="text" className="form-control" id="floatingDocument" placeholder="Document" required autoFocus />
									<label htmlFor="floatingDocument">Document</label>
								</div>
								<div className="form-floating mb-3">
									<input type="text" className="form-control" id="floatingFirstname" placeholder="Firstname" required autoFocus />
									<label htmlFor="floatingFirstname">Firstname</label>
								</div>
								<div className="form-floating mb-3">
									<input type="text" className="form-control" id="floatingLastname" placeholder="Lastname" required autoFocus />
									<label htmlFor="floatingLastname">Lastname</label>
								</div>
								<div className="d-grid mb-2">
									<button className="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit">Register</button>
								</div>
								<a className="d-block text-center mt-2 small" href="/sign">Have an account? Sign In</a>
								<hr className="my-4" />
								<div className="d-grid mb-2">
									<button className="btn btn-lg btn-google btn-login fw-bold text-uppercase" type="submit">
										<i className="fab fa-google me-2"></i> Sign up with Google
									</button>
								</div>
								<div className="d-grid">
									<button className="btn btn-lg btn-facebook btn-login fw-bold text-uppercase" type="submit">
										<i className="fab fa-facebook-f me-2"></i> Sign up with Facebook
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}
export default Register;