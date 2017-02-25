const client = require('./client');
const React = require('react');
const Link = require('react-router').Link;
const moment = require('moment');

class GameHistory extends React.Component {
    render() {
        return (
            <div>
                <GameList idSelectedPlayer={this.props.params.idSelectedPlayer}
                          idSelectedGame={this.props.params.idSelectedGame}/>
                <GameDetail idSelectedPlayer={this.props.params.idSelectedPlayer}
                            idSelectedGame={this.props.params.idSelectedGame}/>
            </div>
        );
    }
}

class GameList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {games: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/rocketleague/game-history?playerId=' + this.props.idSelectedPlayer}).done(response => {
            this.setState({games: response.entity.gameSummaries});
        });
    }

    render() {
        var filteredGames = this.state.games.filter(gameToFilter => this.props.idSelectedGame == null? true : gameToFilter.idGame == this.props.idSelectedGame);
        var games = filteredGames.map(game =>
            <Game key={game.idGame} game={game} idSelectedPlayer={this.props.idSelectedPlayer}/>
        );

        return (
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
        )
    }
}

class Game extends React.Component {
    render() {
        return (
            <Link to={"/rocketleague/" + this.props.idSelectedPlayer + "/game-history/game/" + this.props.game.idGame}
                  className="row clickable">
                <div className="leftcell">{moment(this.props.game.date).format('DD-MMM-YYYY HH:mm')}</div>
                <div className="cell">{this.props.game.playlist}</div>
                <div className="cell">{this.props.game.competitiveInd ? 'Competitive' : 'Friendly'}</div>
                <div className="cell">{this.getScore()}</div>
                <div className="cell">{this.props.game.winLoss}</div>
            </Link>
        )
    }

    getScore() {
        if (this.props.game.winLoss === "WIN" && (this.props.game.orangeScore > this.props.game.blueScore)) {
            return (this.props.game.orangeScore + ' - ' + this.props.game.blueScore);
        }
        return (this.props.game.blueScore + ' - ' + this.props.game.orangeScore);
    }
}

class GameDetail extends React.Component {
    constructor(props) {
        super(props);
        this.state = {orangePerformances: [], bluePerformances: [], orangeScore: null, blueScore: null};
    }

    componentDidMount() {
        this.loadGameDetail(this.props.idSelectedGame);
    }

    componentWillReceiveProps(nextProps) {
        this.loadGameDetail(nextProps.idSelectedGame);
    }

    loadGameDetail(idSelectedGame) {
        if (idSelectedGame == null) {
            return;
        }
        client({method: 'GET', path: '/rocketleague/game-detail?gameId=' + idSelectedGame}).done(response => {
            this.setState({
                orangePerformances: response.entity.orangePerformances,
                bluePerformances: response.entity.bluePerformances,
                orangeScore: response.entity.orangeScore,
                blueScore: response.entity.blueScore
            });

        });
    }

    render() {
        if (this.props.idSelectedGame == null) {
            return null;
        }
        var orangePerformances = this.state.orangePerformances.map(performance =>
            <Performance key={performance.idPlayer} performance={performance}/>
        );
        var bluePerformances = this.state.bluePerformances.map(performance =>
            <Performance key={performance.idPlayer} performance={performance}/>
        );

        return (
            <div>
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
                <div className="right">
                    <Link to={"/rocketleague/" + this.props.idSelectedPlayer + "/game-history/"}>
                        <div className="green button">BACK TO GAME LIST</div>
                    </Link>
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
                <div className="cell">{this.props.performance.mvpInd ? 'MVP' : ''}</div>
                <div className="cell">{this.props.performance.score}</div>
                <div className="cell">{this.props.performance.goals}</div>
                <div className="cell">{this.props.performance.assists}</div>
                <div className="cell">{this.props.performance.saves}</div>
                <div className="cell">{this.props.performance.shots}</div>
            </div>
        )
    }
}

export {GameHistory};

