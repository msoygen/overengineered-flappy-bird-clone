/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extensions;

import gameobject.GameObject;

/**
 *
 * @author msoyg
 */
public class PlayerCollision {

    public boolean checkBottomPipeCollision(GameObject player, GameObject pipe) {
        return player.getDestRect().x() + player.getDestRect().width() / 2 - 10 > pipe.getDestRect().x()
                && player.getDestRect().x() + player.getDestRect().width() / 2 - 10 < pipe.getDestRect().x() + pipe.getDestRect().width()
                && player.getDestRect().y() + player.getDestRect().height() / 2 - 10 > pipe.getDestRect().y();
    }

    public boolean checkTopPipeCollision(GameObject player, GameObject pipe) {
        return player.getDestRect().x() + player.getDestRect().width() / 2 - 10 > pipe.getDestRect().x()
                && player.getDestRect().x() < pipe.getDestRect().x() + pipe.getDestRect().width()
                && player.getDestRect().y() - player.getDestRect().height() / 2 + 5 < pipe.getDestRect().y() + pipe.getDestRect().height();
    }

    public boolean checkPlayerPassedCurrentPipe(GameObject player, GameObject pipe) {
        return player.getDestRect().x() + player.getDestRect().width() / 2 > pipe.getDestRect().x() + pipe.getDestRect().width()
                && player.getDestRect().x() + player.getDestRect().width() / 2 < pipe.getDestRect().x() + pipe.getDestRect().width() + player.getDestRect().width();
    }

    public boolean checkPlayerCollidesCoin(GameObject player, GameObject coin) {
        return player.getDestRect().x() + player.getDestRect().width() / 2 - 10 > coin.getDestRect().x()
                && player.getDestRect().x() < coin.getDestRect().x() + coin.getDestRect().width()
                && player.getDestRect().y() - player.getDestRect().height() / 2 + 5 < coin.getDestRect().y() + coin.getDestRect().height();
    }
}
