const React = require('react');
const Link = require('react-router').Link;

var tabList = [
    {'id': 0, 'name': 'GAME HISTORY', 'url': '/game-history'},
    {'id': 1, 'name': 'LAST 10 GAMES', 'url': '/form'},
    {'id': 2, 'name': 'AVERAGES', 'url': '/averages'},
    {'id': 3, 'name': 'CONTRIBUTIONS', 'url': '/contributions'},
    {'id': 4, 'name': 'MISC', 'url': '/misc'}
];

class TabbedPlayerDetailContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {currentTab: 0, tabList: tabList, playlist: "ALL", gameType: "ALL"};

        this.changeTab = this.changeTab.bind(this);
        this.onSelectPlaylist = this.onSelectPlaylist.bind(this);
        this.onSelectGameType = this.onSelectGameType.bind(this);
    }

    changeTab(tab) {
        this.setState({currentTab: tab.id});
    }

    onSelectPlaylist(event) {
        this.setState({playlist: event.target.value});
    }

    onSelectGameType(event) {
        this.setState({gameType: event.target.value});
    }

    render() {
        return (
            <div className="wrapper">
                <div className="horizontal">
                    <Link to="/rocketleague" className="playername">{this.props.params.idSelectedPlayer}</Link>
                    <select className="green valign" onChange={this.onSelectPlaylist} value={this.state.playlist}>
                        <option value="ALL">ALL PLAYLISTS</option>
                        <option value="STANDARD">STANDARD</option>
                        <option value="DOUBLES">DOUBLES</option>
                        <option value="CHAOS">CHAOS</option>
                    </select>
                    <select className="green valign" onChange={this.onSelectGameType} value={this.state.gameType}>
                        <option value="ALL">ALL GAMETYPES</option>
                        <option value="COMPETITIVE">COMPETITIVE</option>
                        <option value="FRIENDLY">FRIENDLY</option>
                    </select>
                    {/*{<Link to="/rocketleague" className="green button valign">PLAYER LIST</Link>}*/}
                </div>
                <Tabs currentTab={this.state.currentTab}
                      changeTab={this.changeTab}
                      tabList={this.state.tabList}
                      idSelectedPlayer={this.props.params.idSelectedPlayer}/>
                {React.cloneElement(this.props.children, {playlist: this.state.playlist, gameType: this.state.gameType })}
            </div>
        )
    }
}

class Tabs extends React.Component {
    handleClick(tab) {
        this.props.changeTab(tab);
    }

    render() {
        return (
            <nav>
                <ul>
                    {this.props.tabList.map(function (tab) {
                        return (
                            <Tab handleClick={this.handleClick.bind(this, tab)}
                                 key={tab.id}
                                 url={tab.url}
                                 name={tab.name}
                                 isCurrent={this.props.currentTab === tab.id}
                                 idSelectedPlayer={this.props.idSelectedPlayer}/>
                        );
                    }.bind(this))}
                </ul>
            </nav>
        )
    }
}

class Tab extends React.Component {

    constructor(props) {
        super(props);
        this.state = {currentTab: 0, tabList: tabList};

        this.handleClick = this.handleClick.bind(this);
    }

    handleClick(e) {
        e.preventDefault();
        this.props.handleClick(e);
    }

    render() {
        return (
            <li onClick={this.handleClick}>
                <Link to={"/rocketleague/" + this.props.idSelectedPlayer + this.props.url} activeClassName="red">
                    {this.props.name}
                </Link>
            </li>

        )
    }
}

export {TabbedPlayerDetailContainer};