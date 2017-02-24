const React = require('react');
const Link = require('react-router').Link;

var tabList = [
    {'id': 0, 'name': 'GAME HISTORY', 'url': '/game-history'},
    {'id': 1, 'name': 'PLAYER DETAILS', 'url': '/player-details'},
    {'id': 2, 'name': 'TEAM DETAILS', 'url': '/team-details'},
    {'id': 3, 'name': 'LEADERBOARDS', 'url': '/leaderboards'}
];

class TabbedPlayerDetailContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {currentTab: 0, tabList: tabList};

        this.changeTab = this.changeTab.bind(this);
    }

    changeTab(tab) {
        this.setState({currentTab: tab.id});
        console.log("Current tab is: " + tab.id);
    }

    render() {
        return (
            <div className="wrapper">
                <div className="horizontal">
                    <div className="playername">{this.props.params.idSelectedPlayer}</div>
                    <Link to="/" className="green button valign">BACK TO PLAYER LIST</Link>
                </div>
                <Tabs currentTab={this.state.currentTab}
                      changeTab={this.changeTab}
                      tabList={this.state.tabList}
                      idSelectedPlayer={this.props.params.idSelectedPlayer}/>
                {this.props.children}
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
            <li className={this.props.isCurrent ? 'current' : null} onClick={this.handleClick}>
                <Link to={"/" + this.props.idSelectedPlayer + this.props.url} className="a">
                    {this.props.name}
                </Link>
            </li>

        )
    }
}

export {TabbedPlayerDetailContainer};