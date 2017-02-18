const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');
const moment = require('moment');
var Router = require('react-router').Router
var Route = require('react-router').Route
var IndexRoute = require('react-router').IndexRoute
var Link = require('react-router').Link
var browserHistory = require('react-router').browserHistory

class App extends React.Component {
    render() {
        return (
            <div>
                {this.props.children}
            </div>
        )
    }
}

class GameList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {games: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/game-history?playerId=' + this.props.params.idSelectedPlayer}).done(response => {
            this.setState({games: response.entity.gameSummaries});
        });
    }

    render() {
        var games = this.state.games.map(game =>
            <Game key={game.idGame} game={game}/>
        );
        return (
            <div className="wrapper">
                <div className="table">
                    <div className="row header">
                        <div className="leftcell">DATE</div>
                        <div className="cell">PLAYLIST</div>
                        <div className="cell">GAME TYPE</div>
                        <div className="cell">SCORE</div>
                        <div className="cell">W/L</div>
                    </div>
                    {games}
                </div>
            </div>
        )
    }
}

class Game extends React.Component {
    render() {
        return (
            <Link to={"/game-detail/" + this.props.game.idGame} className="row">
                <div className="leftcell">{moment(this.props.game.date).format('DD-MMM-YYYY HH:mm')}</div>
                <div className="cell">{this.props.game.playlist}</div>
                <div className="cell">{this.props.game.competitiveInd ? 'Competitive' : 'Friendly'}</div>
                <div className="cell">{this.props.game.orangeScore + ' - ' + this.props.game.blueScore}</div>
                <div className="cell">{this.props.game.winLoss}</div>
            </Link>
        )
    }
}

class PerformanceList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {orangePerformances: [], bluePerformances: [], orangeScore: null, blueScore: null};
    }

    componentDidMount() {
        client({method: 'GET', path: '/game-detail?gameId=' + this.props.params.idSelectedGame}).done(response => {
            this.setState({
                orangePerformances: response.entity.orangePerformances,
                bluePerformances: response.entity.bluePerformances,
                orangeScore: response.entity.orangeScore,
                blueScore: response.entity.blueScore
            });
        });
    }

    render() {
        var orangePerformances = this.state.orangePerformances.map(performance =>
            <Performance key={performance.idPlayer} performance={performance}/>
        );
        var bluePerformances = this.state.bluePerformances.map(performance =>
            <Performance key={performance.idPlayer} performance={performance}/>
        );

        return (

            <div className="wrapper">
                <div className="table">
                    <div className="row header blue">
                        <div className="cell score">{this.state.blueScore}</div>
                        <div className="cell"></div>
                        <div className="cell"></div>
                        <div className="cell">SCORE</div>
                        <div className="cell">GOALS</div>
                        <div className="cell">ASSISTS</div>
                        <div className="cell">SAVES</div>
                        <div className="cell">SHOTS</div>
                    </div>
                    {bluePerformances}
                    <div className="row header orange">
                        <div className="cell score">{this.state.orangeScore}</div>
                        <div className="cell"></div>
                        <div className="cell"></div>
                        <div className="cell">SCORE</div>
                        <div className="cell">GOALS</div>
                        <div className="cell">ASSISTS</div>
                        <div className="cell">SAVES</div>
                        <div className="cell">SHOTS</div>
                    </div>
                    {orangePerformances}
                </div>
            </div>
        )
    }
}

class Performance extends React.Component {
    render() {
        return (
            <div className="row">
                <div className="cell blank"></div>
                <div className="leftcell">{this.props.performance.idPlayer}</div>
                <div className="cell">{this.props.performance.mvpInd? "MVP" : ""}</div>
                <div className="cell">{this.props.performance.score}</div>
                <div className="cell">{this.props.performance.goals}</div>
                <div className="cell">{this.props.performance.assists}</div>
                <div className="cell">{this.props.performance.saves}</div>
                <div className="cell">{this.props.performance.shots}</div>
            </div>
        )
    }
}

class PlayerList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {players: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/tracked-players/all'}).done(response => {
            this.setState({players: response.entity.playerSummaries});
        });
    }

    render() {
        var players = this.state.players.map(player =>
            <Player key={player.idPlayer} player={player}/>
        );
        return (

            <div className="wrapper">
                <div className="table">
                    <div className="row header">
                        <div className="cell"></div>
                        <div className="cell" title="Games Played">PLAYED</div>
                        <div className="cell" title="Win Percentage">WIN %</div>
                        <div className="cell" title="Total Goals">GOALS</div>
                        <div className="cell" title="Total Assists">ASSISTS</div>
                        <div className="cell" title="Total Saves">SAVES</div>
                        <div className="cell" title="Goals per Game">AVG GOALS</div>
                        <div className="cell" title="Assists per Game">AVG ASSISTS</div>
                        <div className="cell" title="Saves per Game">AVG SAVES</div>
                    </div>
                    {players}
                </div>
            </div>
        )
    }
}

class Player extends React.Component {
    render() {
        return (
            <Link to={"/game-history/" + this.props.player.idPlayer} className="row">
                <div className="leftcell">{this.props.player.idPlayer}</div>
                <div className="cell">{this.props.player.gamesPlayed}</div>
                <div className="cell">{this.props.player.winPercentage.toFixed(2)}</div>
                <div className="cell">{this.props.player.goals}</div>
                <div className="cell">{this.props.player.assists}</div>
                <div className="cell">{this.props.player.saves}</div>
                <div className="cell">{this.props.player.averageGoals.toFixed(2)}</div>
                <div className="cell">{this.props.player.averageAssists.toFixed(2)}</div>
                <div className="cell">{this.props.player.averageSaves.toFixed(2)}</div>
            </Link>
        )
    }
}

ReactDOM.render((
        <Router history={browserHistory}>
            <Route path="/" component={App}>
                <IndexRoute component={PlayerList}/>
                <Route path="game-history/:idSelectedPlayer" component={GameList}/>
                <Route path="game-detail/:idSelectedGame" component={PerformanceList}/>
            </Route>
        </Router>
    ), document.getElementById('react')
)