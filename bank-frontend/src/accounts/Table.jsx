import { useState, useEffect } from 'react';
import './Table.css'

function Table({ bankName, apiUrl, userId }) {
	const [accounts, setAccounts] = useState([]);
	// Get the User Bank Accounts
	useEffect(() => {
		const fetchData = async () => {
			try {
				const response = await fetch(apiUrl + "/u", {
					method: "POST",
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify({ userId })
				});
				if (!response.ok) {
					throw new Error("Network response wasnt ok.");
				}
				const data = await response.json();
				setAccounts(data);
			} catch (error) {
				console.error("Error fetching data: ", error);
			}
		};
		fetchData();
	},);
	// Create a New Account
	const handleCreateBankAccount = async () => {
		try {
			const response = await fetch(apiUrl, {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ userId })
			});
			if (!response.ok) {
				throw new Error('Failed to create account.');
			}
			console.log('Account created successfully.');
			window.location.reload();
		} catch (error) {
			console.error('Error creating account: ', error);
		}
	};
	// Deposit Money to the Selected Account
	const handleDeposit = async (accountId) => {
		try {
			const depositAmount = parseFloat(prompt('Ingrese el monto a depositar:', '0'));
			if (isNaN(depositAmount)) {
				throw new Error('El monto ingresado no es válido.');
			}
			const response = await fetch(apiUrl + "/deposit", {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ accountId, amount: depositAmount })
			});
			if (!response.ok) {
				throw new Error('Failed to deposit amount.');
			}
			console.log('Deposit successful.');
			window.location.reload();
		} catch (error) {
			console.error('Error depositing amount: ', error);
		}
	};
	// Withdraw Money to the Selected Account
	const handleWithdraw = async (accountId) => {
		try {
			const withdrawAmount = parseFloat(prompt('Ingrese el monto a retirar:', '0'));
			if (isNaN(withdrawAmount)) {
				throw new Error('El monto ingresado no es válido.');
			}
			const response = await fetch(apiUrl + "/withdraw", {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ accountId, amount: withdrawAmount })
			});
			if (!response.ok) {
				throw new Error('Failed to withdraw amount.');
			}
			console.log('Withdrawal successful.');
			// Recargar la página después del retiro
			window.location.reload();
		} catch (error) {
			console.error('Error withdrawing amount: ', error);
		}
	};
	return (
		<div class="accounts-table">
			<div class="container-fluid">
				<div class="row justify-content-center">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h3>{bankName} Accounts</h3>
								<button className="btn btn-success" onClick={handleCreateBankAccount}>Create Account</button>
								<div class="table-responsive">
									<table class="table table-hover mb-0">
										<thead>
											<tr>
												<th scope="col">Account ID</th>
												<th scope="col">Creation Date</th>
												<th scope="col">Update Date</th>
												<th scope="col">Balance</th>
												<th scope="col"></th>
												<th scope="col"></th>
											</tr>
										</thead>
										<tbody>
											{
												accounts.map((account) => (
													<tr key={account.id} class="align-items-center">
														<td>{account.id}</td>
														<td>{account.creationDate}</td>
														<td>{account.updateDate}</td>
														<td>{account.balance}</td>
														<td>
															<button className="btn btn-sm btn-primary" onClick={() => handleDeposit(account.id)}>Deposit</button>
														</td>
														<td>
															<button className="btn btn-sm btn-danger" type="button" onClick={() => handleWithdraw(account.id)}>Withdraw</button>
														</td>
													</tr>
												))
											}
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}
export default Table;