import heapq

class CodeTreeLand:
    def __init__(self, n, m, edges):
        self.INF = 1e10
        self.n = n
        self.m = m
        self.edges = edges
        self.existID = {}
        self.product = []
        self.distance = []

        self.graph = [[self.INF] * n for _ in range(n)]
        for i in range(n):
            self.graph[i][i] = 0
        for i in range(0, len(edges), 3):
            self.graph[edges[i]][edges[i+1]] = min(edges[i+2], self.graph[edges[i]][edges[i+1]])
            self.graph[edges[i+1]][edges[i]] = min(edges[i+2], self.graph[edges[i]][edges[i+1]])

        self.dijkstra(0)
        self.commands = {
            200: self.create_product,
            300: self.delete_product,
            400: self.sell_product,
            500: self.change_depart
        }

    def dijkstra(self, s):
        distance = [self.INF] * self.n
        distance[s] = 0
        q = []
        heapq.heappush(q, (0, s))
        while q:
            dist, now = heapq.heappop(q)
            if distance[now] < dist:
                continue
            
            for to in range(self.n):
                cost = dist + self.graph[now][to]
                if cost < distance[to]:
                    distance[to] = cost
                    heapq.heappush(q, (cost, to))

        self.distance = distance

    def command(self, commands_num, *args):
        data = args[0]
        self.commands[commands_num](*data)

    def create_product(self, id, revenue, dest):
        self.existID[id] = id
        heapq.heappush(self.product, (-(revenue - self.distance[dest]), id, revenue, dest))
    
    def delete_product(self, id):
        if self.existID.get(id):
            del self.existID[id]

    def sell_product(self):
        flag = True
        while self.product and flag:
            profit, id, revenue, dest = self.product[0]
            if not self.existID.get(id):
                heapq.heappop(self.product)
            else:
                flag = False

        if self.product:
            profit, id, revenue, dest = self.product[0]
            if profit > 0:
                print(-1)
            else:
                profit, id, revenue, dest = heapq.heappop(self.product)
                del self.existID[id]
                print(id)
        else:
            print(-1)

    def change_depart(self, s):
        self.dijkstra(s)
        for i in range(len(self.product)):
            profit, id, revenue, dest = self.product[i]
            self.product[i] = (-(revenue - self.distance[dest]), id, revenue, dest)
        heapq.heapify(self.product)


Q = int(input())
mk_code_tree_land = list(map(int, input().split()))
code_tree_land = CodeTreeLand(mk_code_tree_land[1], mk_code_tree_land[2], mk_code_tree_land[3:])

for _ in range(Q-1):
    command = list(map(int, input().split()))
    code_tree_land.command(command[0], command[1:])
