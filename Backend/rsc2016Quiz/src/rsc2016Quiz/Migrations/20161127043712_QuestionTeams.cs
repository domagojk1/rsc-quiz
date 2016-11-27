using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore.Migrations;

namespace rsc2016Quiz.Migrations
{
    public partial class QuestionTeams : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "TeamQuestions",
                columns: table => new
                {
                    QuestionId = table.Column<int>(nullable: false),
                    TeamId = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_TeamQuestions", x => new { x.QuestionId, x.TeamId });
                    table.ForeignKey(
                        name: "FK_TeamQuestions_Questions_QuestionId",
                        column: x => x.QuestionId,
                        principalTable: "Questions",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_TeamQuestions_Teams_TeamId",
                        column: x => x.TeamId,
                        principalTable: "Teams",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_TeamQuestions_QuestionId",
                table: "TeamQuestions",
                column: "QuestionId");

            migrationBuilder.CreateIndex(
                name: "IX_TeamQuestions_TeamId",
                table: "TeamQuestions",
                column: "TeamId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "TeamQuestions");
        }
    }
}
