# Bowling game
Bowling game score calculation algorithm.

**Game Simulation:**
Solution contains a Simulation module to simulate several Games
with multiple Players. Each Player extends Thread and throws random
balls and wait for each other.

**Bowling game scoring - Solution Approach:**
General solution approach is to encapsulate Frame related data and
score calculation details from Bowling Game layer. Moreover, to
facilitates Strike/Spare score calculations Next first and Next second ball
result links are created for each ball result object.
As each Frame can have different number of Balls, each frame will
have dynamic size List of Balls. And also all these Balls will have link to
the next Ball in order to help calculating Strike/Spare scores.
Score board calculate final game score by summing scores of each
Frames and it is not interested in how score of each Frame is calculated
in detail level.
As Last Frame differs from normal frames, it is derived from Frame
class to override some methods.
