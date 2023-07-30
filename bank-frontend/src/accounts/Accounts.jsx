import './Accounts.css'
import Table from './Table';
import { useParams } from 'react-router-dom';

function Accounts() {
	const { userId } = useParams();
	return (
		<div class="container d-flex align-items-center justify-content-center vh-100">
			<div class="container">
				<Table bankName="Arequipa" apiUrl="http://localhost:8085/api/accounts" userId={ userId } />
				<Table bankName="Lima" apiUrl="http://localhost:8086/api/accounts" userId={ userId } />
				<Table bankName="Cusco" apiUrl="http://localhost:8087/api/accounts" userId={ userId } />
			</div>
		</div>
	);
}

export default Accounts;