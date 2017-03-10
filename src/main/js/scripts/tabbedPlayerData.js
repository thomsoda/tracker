const React = require('react');
const Link = require('react-router').Link;

var tabList = [
    {'id': 0, 'name': 'GAME HISTORY', 'url': '/game-history'},
    {'id': 1, 'name': 'AVERAGES', 'url': '/averages'},
    {'id': 2, 'name': 'CONTRIBUTIONS', 'url': '/contributions'},
    {'id': 3, 'name': 'FORM', 'url': '/form'},
    {'id': 4, 'name': 'MISC', 'url': '/misc'}
];

class TabbedPlayerDetailContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {currentTab: 0, tabList: tabList};

        this.changeTab = this.changeTab.bind(this);
    }

    changeTab(tab) {
        this.setState({currentTab: tab.id});
    }

    render() {
        return (
            <div className="wrapper">
                <div className="horizontal">
                    <div className="playername">{this.props.params.idSelectedPlayer}</div>
                    <Link to="/rocketleague" className="green button valign">BACK TO PLAYER LIST</Link>
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
            <li onClick={this.handleClick}>
                <Link to={"/rocketleague/" + this.props.idSelectedPlayer + this.props.url} activeClassName="red">
                    {this.props.name}
                </Link>
            </li>

        )
    }
}

export {TabbedPlayerDetailContainer};