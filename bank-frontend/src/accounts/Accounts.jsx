import './Accounts.css'
import Table from './Table';

function Accounts() {
	return (
		<div className="container">
			<Table bankName="Arequipa" apiUrl="http://localhost:8085/api/accounts/u" />
			<Table bankName="Lima" apiUrl="http://localhost:8086/api/accounts/u" />
			<Table bankName="Cusco" apiUrl="http://localhost:8087/api/accounts/u" />
		</div>
	);
}

export default Accounts;