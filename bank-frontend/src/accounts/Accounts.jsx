import './Accounts.css'
import Table from './Table';
import { useParams } from 'react-router-dom';

function Accounts() {
	const { document } = useParams();
	return (
		<div className="container d-flex align-items-center justify-content-center vh-90">
			<div className="container">
				<Table bankName="Arequipa" apiUrl="http://localhost:8085/api/accounts" document={ document } />
				<Table bankName="Lima" apiUrl="http://localhost:8086/api/accounts" document={ document } />
				<Table bankName="Cusco" apiUrl="http://localhost:8087/api/accounts" document={ document } />
			</div>
		</div>
	);
}

export default Accounts;