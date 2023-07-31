import { useState, useEffect } from 'react';
import './Table.css'

function Table({ bankName, apiUrl, document }) {
	const [accounts, setAccounts] = useState([]);
	// Get the User Bank Accounts
	useEffect(() => {
		const fetchData = async () => {
			try {
				const response = await fetch(apiUrl + "/d", {
					method: "POST",
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify({ document })
				});
				if (!response.ok) {
					throw new Error("Network response wasnt ok.");
				}
				const data = await response.json();
				setAccounts(data);
			} catch (error) {
				console.error("Error fetching data.");
			}
		};
		fetchData();
	}, [apiUrl, document]);
	// Create a New Account
	const handleCreateBankAccount = async () => {
		try {
			const response = await fetch(apiUrl, {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ document })
			});
			if (!response.ok) {
				throw new Error('Failed to create account.');
			}
			console.log('Account created successfully.');
			window.location.reload();
		} catch (error) {
			console.error('Error creating account.');
		}
	};
	// Deposit Money to the Selected Account
	const handleDeposit = async (accountId) => {
		try {
			const depositAmount = parseFloat(prompt('Enter the amount to deposit:', '0'));
			if (isNaN(depositAmount)) {
				throw new Error('The amount is invalid.');
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
			console.error('Error depositing amount.');
		}
	};
	// Withdraw Money to the Selected Account
	const handleWithdraw = async (accountId) => {
		try {
			const withdrawAmount = parseFloat(prompt('Enter the amount to withdraw:', '0'));
			if (isNaN(withdrawAmount)) {
				throw new Error('The amount is invalid.');
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
			window.location.reload();
		} catch (error) {
			console.error('Error withdrawing amount.');
		}
	};
	return (
		<div className="accounts-table">
			<div className="container-fluid">
				<div className="row justify-content-center">
					<div className="col-12">
						<div className="card">
							<div className="card-body">
								<h3>{bankName} Accounts</h3>
								<button className="btn btn-success" onClick={handleCreateBankAccount}>Create Account</button>
								<div className="table-responsive">
									<table className="table table-hover mb-0">
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
													<tr key={account.id} className="align-items-center">
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