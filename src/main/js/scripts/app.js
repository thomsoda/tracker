const React = require('react');
const ReactDOM = require('react-dom')

var Router = require('react-router').Router
var Route = require('react-router').Route
var IndexRoute = require('react-router').IndexRoute
var browserHistory = require('react-router').browserHistory

const PlayerList = require('./playerList').PlayerList;
const TabbedPlayerData = require('./tabbedPlayerData').TabbedPlayerDetailContainer;
const GameHistory = require('./gameHistory').GameHistory;
const PlayerDetails = require('./playerDetails').PlayerDetails;
const TeamDetails = require('./teamDetails').TeamDetails;
const Leaderboards = require('./leaderboards').Leaderboards;

class App extends React.Component {
    render() {
        return (
            <div>
                {this.props.children}
            </div>
        )
    }
}

ReactDOM.render((
        <Router history={browserHistory}>
            <Route path="/" component={App}>
                <IndexRoute component={PlayerList}/>
                <Route path=":idSelectedPlayer" component={TabbedPlayerData}>
                    <IndexRoute component={GameHistory}/>
                    <Route path="game-history(/game/:idSelectedGame)" component={GameHistory}/>
                    <Route path="player-details" component={PlayerDetails}/>
                    <Route path="team-details" component={TeamDetails}/>
                    <Route path="leaderboards" component={Leaderboards}/>
                </Route>
            </Route>
        </Router>
    ), document.getElementById('react')
)