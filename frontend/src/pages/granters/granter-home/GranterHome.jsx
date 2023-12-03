import Header from '../../../components/header/header-no-background/Header';
import GranterUpdates from './granter-updates/GranterUpdates';
import Footer from '../../../components/footer/Footer';
import './granter_home.css'

function GranterHome() {
    return (
        <div className="granterHome">
            <Header isGrant={true}></Header>
            <GranterUpdates></GranterUpdates>
            {/*<Footer></Footer>*/}
        </div>
    );
}

export default GranterHome;