import { useState, useEffect } from 'react';
import './Table.css'

function Table({ bankName, apiUrl }) {
	const [accounts, setAccounts] = useState([]);
	const userId = 2;
	useEffect(() => {
		const fetchData = async () => {
			try {
				const response = await fetch(apiUrl, {
					method: "POST",
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify({ userId })
				});
				if (!response.ok) {
					throw new Error("Network responser wasnt ok.");
				}
				const data = await response.json();
				setAccounts(data);
			} catch (error) {
				console.error("Error fetching data: ", error);
			}
		};
		fetchData();
	}, [apiUrl]);
	return (
		<div className="accounts-table">
			<div className="container">
				<div className="row justify-content-center">
					<div className="col-12">
						<div className="card">
							<div className="card-body">
								<h3>{bankName} Accounts</h3>
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
															<button class="btn btn-sm btn-primary" type="submit">Deposit</button>
														</td>
														<td>
															<button class="btn btn-sm btn-danger" type="submit">Withdraw</button>
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